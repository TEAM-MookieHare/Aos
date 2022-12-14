package com.mookiehare.hohoi.feature.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mookiehare.hohoi.feature.chat.ChatRoute

const val chatNavigationRoute = "chat_route"

fun NavController.navigateToChat(navOptions: NavOptions? = null) {
    this.navigate(chatNavigationRoute, navOptions)
}

fun NavGraphBuilder.chatScreen() {
    composable(route = chatNavigationRoute) {
        ChatRoute()
    }
}