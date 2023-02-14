package com.mookiehare.hohoi.feature.mapmatching

import android.Manifest
import android.app.Activity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mookiehare.hohoi.core.design.component.HelloiChip
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.google.maps.android.clustering.ClusterManager
import com.mookiehare.hohoi.core.design.component.Chip
import com.mookiehare.hohoi.feature.mapmatching.model.Location
import com.mookiehare.hohoi.feature.mapmatching.model.MarkerItem

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
    items.add(MarkerItem(LatLng(37.5480947960,126.87617507), "Marker", "Snippet"))
    items.add(MarkerItem(LatLng(37.548347622,126.87715100), "Marker", "Snippet"))
    items.add(MarkerItem(LatLng(37.548147622,126.87715100), "Marker", "Snippet"))
    items.add(MarkerItem(LatLng(37.548547622,126.87715100), "Marker", "Snippet"))
//    items.add(MarkerItem(LatLng(37.548247622,126.87715100), "Marker", "Snippet"))

    val locationState by viewModel.lastSelectedLocation.collectAsStateWithLifecycle()
    
    MapMatchingScreen(
        modifier = modifier,
        viewModel,
        items,
        locationState
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun MapMatchingScreen(
    modifier: Modifier = Modifier,
    viewModel: MapMatchingViewModel = hiltViewModel(),
    items: List<MarkerItem> = listOf(),
    location: Location
) {

    val activity = LocalView.current.context as Activity
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }

    PermissionsRequired(
        multiplePermissionsState = multiplePermissionState,
        permissionsNotGrantedContent = { /* ... */ },
        permissionsNotAvailableContent = { /* ... */ }
    ){
        viewModel.getCurrentLocation(activity)

        LaunchedEffect(location) {
            cameraPositionState.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(
                        LatLng(
                            location.lat,
                            location.lng
                        ), 15f)),
                1_000)
        }

        GoogleMap(
            modifier = modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true)
        ) {
            rememberMarkerState()
            val context = LocalContext.current
            var clusterManager by remember { mutableStateOf<ClusterManager<MarkerItem>?>(null) }
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

        Column {

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = "${items.size}명이 주변에 있어요!",
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            RandomMatchingChips(elements = chipsDummyData)
        }



    }
}

@Composable
fun RandomMatchingChips(
    elements : List<Chip>
){
    HohoiTheme {
        Surface(color = Color.Transparent) {
            LazyRow(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(elements.size){ index ->
                    HelloiChip(
                        selected = elements[index].isSelected.value,
                        onSelectedChange = { checked -> elements[index].isSelected.value = checked }
                    ) {
                        Text(text = elements[index].text)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapMatchingScreenPreview() {
    HohoiTheme {
//        MapMatchingScreen()
    }
}