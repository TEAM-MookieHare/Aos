package com.mookiehare.hohoi.feature.chat.navigation

import androidx.compose.animation.*
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
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

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.chatScreen(
    navigateToChatDetail: (String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = chatGraph,
        startDestination = chatNavigationRoute
    ) {
        composable(
            route = chatNavigationRoute,
            enterTransition = { scaleIn() },
            exitTransition = { scaleOut() },
            popEnterTransition = { scaleIn() },
            popExitTransition = { scaleOut() }
        ) {
            ChatListRoute(navigateToChatDetail = navigateToChatDetail)
        }

        composable(
            route = "$chatDetailNavigationRoute/{$chatDetailIdArg}",
            arguments = listOf(
                navArgument(chatDetailIdArg) { type = NavType.StringType }
            ),
            enterTransition = { scaleIn() },
            exitTransition = { scaleOut() },
            popEnterTransition = { scaleIn() },
            popExitTransition = { scaleOut() }
        ) {
            ChatDetailRoute()
        }

        nestedGraphs()
    }
}