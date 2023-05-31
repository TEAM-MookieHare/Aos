package com.mookiehare.hohoi.feature.mapmatching

import android.Manifest
import android.app.Activity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mookiehare.hohoi.core.design.component.HelloiChip
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.google.maps.android.clustering.ClusterManager
import com.mookiehare.hohoi.core.design.component.Chip
import com.mookiehare.hohoi.feature.mapmatching.model.Location
import com.mookiehare.hohoi.feature.mapmatching.model.MarkerItem
import androidx.compose.runtime.rememberCoroutineScope
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import kotlinx.coroutines.launch


val chipsDummyData  =
    listOf(
        Chip("남자", mutableStateOf(value = false)),
        Chip("여자", mutableStateOf(value = false)),
        Chip("애견", mutableStateOf(value = false)),
        Chip("독서", mutableStateOf(value = false)),
        Chip("애견", mutableStateOf(value = false)),
        Chip("외국인", mutableStateOf(value = false)),
        Chip("산책", mutableStateOf(value = false)),
        Chip("맛집탐방", mutableStateOf(value = false))
    )

@Composable
internal fun MapMatchingRoute(
    modifier: Modifier = Modifier,
    viewModel: MapMatchingViewModel = hiltViewModel()
) {

    val items = remember { mutableStateListOf<MarkerItem>() }
    items.clear()
    items.add(MarkerItem(LatLng(37.5480947960,126.87617507), "태양", "test1"))
    items.add(MarkerItem(LatLng(37.548347622,126.87715100), "현화", "test2"))
    items.add(MarkerItem(LatLng(37.548147622,126.87715100), "현묵", "test3"))
    items.add(MarkerItem(LatLng(37.548547622,126.87715100), "강철", "test4"))
    items.add(MarkerItem(LatLng(37.548247622,126.87715100), "하민", "test5"))

    val locationState by viewModel.lastSelectedLocation.collectAsStateWithLifecycle()

    MapMatchingScreen(
        modifier = modifier,
        viewModel,
        items,
        locationState
    )
}

@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
internal fun MapMatchingScreen(
    modifier: Modifier = Modifier,
    viewModel: MapMatchingViewModel = hiltViewModel(),
    items: List<MarkerItem> = listOf(),
    location: Location = Location(0.0, 0.0)
) {

    val activity = LocalView.current.context as Activity
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }

    PermissionsRequired(
        multiplePermissionsState = multiplePermissionState,
        permissionsNotGrantedContent = { /* ... */ },
        permissionsNotAvailableContent = { /* ... */ }
    ){
        viewModel.getCurrentLocation(activity)

        if(location.lat != 0.0 && location.lng != 0.0){

            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(
                    location.lat,
                    location.lng
                ), 17f)
            }

            GoogleMap(
                modifier = modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true)
            ) {
                rememberMarkerState()
                val context = LocalContext.current
                var clusterManager by remember { mutableStateOf<ClusterManager<MarkerItem>?>(null) }.apply {
                    this.value?.setOnClusterClickListener {
                        coroutineScope.launch {
                            sheetState.show()
                        }
                        return@setOnClusterClickListener false
                    }
                    this.value?.setOnClusterItemClickListener{
                        coroutineScope.launch {
                            sheetState.show()
                        }
                        return@setOnClusterItemClickListener false
                    }
                }
                MapEffect(items) { map ->
                    if (clusterManager == null) {
                        clusterManager = ClusterManager<MarkerItem>(context, map)
                    }
                    clusterManager?.addItems(items)

                }
                LaunchedEffect(key1 = cameraPositionState.isMoving) {
                    if (!cameraPositionState.isMoving) {
                        clusterManager?.onCameraIdle()
                    }
                }
            }

        }

//        HelloiChip(
//            elements = chipsDummyData,
//            modifier = Modifier.padding(top = 50.dp)
//        )

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = { BottomSheet(items) },
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp),
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }
            ) {
                Text(
                    text = "${items.size}명이 사용하고 있어요!",
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

}

@Composable
fun BottomSheet(messages: List<MarkerItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 30.dp)
    ) {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(msg: MarkerItem) {
    Column(
        modifier = Modifier.padding(6.dp)
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_background),
//            contentDescription = null,
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, androidx.compose.material.MaterialTheme.colors.secondary, CircleShape)
//        )

        Text(
            text = msg.itemTitle,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = msg.itemSnippet,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapMatchingScreenPreview() {
    HohoiTheme {
        MapMatchingScreen()
    }
}