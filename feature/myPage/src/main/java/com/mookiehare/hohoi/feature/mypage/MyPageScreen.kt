package com.mookiehare.hohoi.feature.mypage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mookiehare.hohoi.core.design.theme.HohoiTheme

@Composable
internal fun MyPageRoute(
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel()
) {

    MyPageScreen(
        modifier = modifier
    )
}

@Composable
internal fun MyPageScreen(
    modifier: Modifier = Modifier,
) {
    Text(text = "Hello MyPage Screen!")
}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    HohoiTheme {
        MyPageScreen()
    }
}