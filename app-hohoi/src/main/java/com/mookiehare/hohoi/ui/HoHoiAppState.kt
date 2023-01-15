package com.mookiehare.hohoi.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mookiehare.hohoi.feature.chat.navigation.chatNavigationRoute
import com.mookiehare.hohoi.feature.chat.navigation.navigateToChat
import com.mookiehare.hohoi.feature.mapmatching.navigation.mapMatchingNavigationRoute
import com.mookiehare.hohoi.feature.mapmatching.navigation.navigateToMapMatching
import com.mookiehare.hohoi.feature.mypage.navigation.myPageNavigationRoute
import com.mookiehare.hohoi.feature.mypage.navigation.navigateToMyPage
import com.mookiehare.hohoi.feature.randommatching.navigation.navigateToRandomMatching
import com.mookiehare.hohoi.feature.randommatching.navigation.randomMatchingNavigationRoute
import com.mookiehare.hohoi.navigation.TopLevelDestination
import com.mookiehare.hohoi.navigation.TopLevelDestination.*
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberHoHoiAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberAnimatedNavController()
): HoHoiAppState {
    return remember (navController, coroutineScope, windowSizeClass) {
        HoHoiAppState(navController, coroutineScope, windowSizeClass)
    }
}

class HoHoiAppState (
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            randomMatchingNavigationRoute -> RANDOM_MATCHING
            chatNavigationRoute -> CHAT
            mapMatchingNavigationRoute -> MAP_MATCHING
            myPageNavigationRoute -> MY_PAGE
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * app 내의 Top Level Destination navigating UI Logic 입니다.
     * Top Level Destination 들은 무조건 하나의 back stack destination 정보를 가지고있습니다.
     * navigate 될 때마다 상태가 save 되고 resotre 됩니다.
     *
     * @param topLevelDestination: 이동할 Destination.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // 사용자가 item 을 선택할 때 back stack 에
            // 큰 스택이 쌓이지 않도록 그래프의 시작 대상으로 팝업 표시
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // 동일한 item 을 다시 선택할 때 동일한 item 실행 방지
            launchSingleTop = true
            // 이전에 선택한 item 을 다시 선택할 때 상태 복원
            restoreState = true
        }

        when (topLevelDestination) {
            RANDOM_MATCHING -> navController.navigateToRandomMatching(topLevelNavOptions)
            CHAT -> navController.navigateToChat(topLevelNavOptions)
            MAP_MATCHING -> navController.navigateToMapMatching(topLevelNavOptions)
            MY_PAGE -> navController.navigateToMyPage(topLevelNavOptions)
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}