package com.mookiehare.hohoi.feature.mapmatching

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.mookiehare.hohoi.core.design.theme.HohoiTheme

@Composable
internal fun MapMatchingRoute(
    modifier: Modifier = Modifier,
    viewModel: MapMatchingViewModel = hiltViewModel()
) {

    MapMatchingScreen(
        modifier = modifier
    )
}

@Composable
internal fun MapMatchingScreen(
    modifier: Modifier = Modifier,
) {

    val singapore = LatLng(37.53, 127.02)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
//    Text(text = "태양의 Map개발")
}

@Preview(showBackground = true)
@Composable
fun MapMatchingScreenPreview() {
    HohoiTheme {
        MapMatchingScreen()
    }
}
