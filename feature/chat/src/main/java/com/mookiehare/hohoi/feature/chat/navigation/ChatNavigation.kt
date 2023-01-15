package com.mookiehare.hohoi.feature.chat.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mookiehare.hohoi.feature.chat.chatDetail.ChatDetailRoute
import com.mookiehare.hohoi.feature.chat.chatList.ChatListRoute

const val chatGraph = "chat_graph"
const val chatNavigationRoute = "chat_route"

const val chatDetailNavigationRoute = "chat_detail_route"
const val chatDetailIdArg = "chatDetailId"

internal class ChatDetailArgs(val chatDetailId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle.get(chatDetailIdArg) as? String))
}

fun NavController.navigateToChat(navOptions: NavOptions? = null) {
    this.navigate(chatNavigationRoute, navOptions)
}

fun NavController.navigateToChatDetail(chatDetailId: String){
    this.navigate("$chatDetailNavigationRoute/$chatDetailId")
}

fun NavGraphBuilder.chatScreen(
    navigateToChatDetail: (String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = chatGraph,
        startDestination = chatNavigationRoute
    ) {
        composable(route = chatNavigationRoute) {
            ChatListRoute(navigateToChatDetail = navigateToChatDetail)
        }

        composable(
            route = "$chatDetailNavigationRoute/{$chatDetailIdArg}",
            arguments = listOf(
                navArgument(chatDetailIdArg) { type = NavType.StringType }
            )
        ) {
            ChatDetailRoute()
        }

        nestedGraphs()
    }
}