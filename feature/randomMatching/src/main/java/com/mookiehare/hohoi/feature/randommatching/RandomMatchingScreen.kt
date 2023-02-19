package com.mookiehare.hohoi.feature.randommatching

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mookiehare.hohoi.core.design.component.HelloiChip
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.mookiehare.hohoi.core.design.component.Chip


@Composable
internal fun RandomMatchingRoute(
    modifier: Modifier = Modifier,
    viewModel: RandomMatchingViewModel = hiltViewModel()
) {

    RandomMatchingScreen(
        modifier = modifier
    )
}
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
internal fun RandomMatchingScreen(
    modifier: Modifier = Modifier,
) {
    HelloiChip(elements = chipsDummyData)
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun RandomMatchingPreview() {
    HohoiTheme {
        RandomMatchingScreen()
    }
}