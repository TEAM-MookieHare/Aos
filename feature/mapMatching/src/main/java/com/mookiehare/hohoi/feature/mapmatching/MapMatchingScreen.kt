package com.mookiehare.hohoi.feature.mapmatching

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
    Text(text = "태양의 Map개발")
}

@Preview(showBackground = true)
@Composable
fun MapMatchingScreenPreview() {
    HohoiTheme {
        MapMatchingScreen()
    }
}