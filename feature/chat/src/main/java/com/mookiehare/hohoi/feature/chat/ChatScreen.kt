package com.mookiehare.hohoi.feature.chat

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mookiehare.hohoi.core.design.theme.HohoiTheme

@Composable
internal fun ChatRoute(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {

    ChatScreen(
        modifier = modifier
    )
}

@Composable
internal fun ChatScreen(
    modifier: Modifier = Modifier,
) {
    Text(text = "Hello Chat Screen!")
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    HohoiTheme {
        ChatScreen()
    }
}