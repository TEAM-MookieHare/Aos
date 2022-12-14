package com.mookiehare.hohoi.feature.randommatching

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mookiehare.hohoi.core.design.theme.HohoiTheme


@Composable
internal fun RandomMatchingRoute(
    modifier: Modifier = Modifier,
    viewModel: RandomMatchingViewModel = hiltViewModel()
) {

    RandomMatchingScreen(
        modifier = modifier
    )
}

@Composable
internal fun RandomMatchingScreen(
    modifier: Modifier = Modifier,
) {
    Text(text = "Hello Random Matching Screen!")
}

@Preview(showBackground = true)
@Composable
fun RandomMatchingPreview() {
    HohoiTheme {
        RandomMatchingScreen()
    }
}