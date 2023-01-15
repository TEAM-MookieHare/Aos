package com.mookiehare.hohoi.feature.chat.chatDetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mookiehare.hohoi.core.design.theme.HohoiTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ChatDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {

    val chatDetailId by viewModel.chatDetailId.collectAsStateWithLifecycle()

    ChatDetailScreen(
        modifier = modifier,
        chatDetailId
    )
}

@Composable
internal fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    chatDetailId: String
) {
    Text(text = "chatDetailId = $chatDetailId")
}

@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    HohoiTheme {
        ChatDetailScreen(chatDetailId = "1")
    }
}