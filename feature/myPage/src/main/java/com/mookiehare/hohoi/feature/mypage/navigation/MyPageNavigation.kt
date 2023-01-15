package com.mookiehare.hohoi.feature.mypage.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mookiehare.hohoi.feature.mypage.MyPageRoute

const val myPageNavigationRoute = "my_page_route"

fun NavController.navigateToMyPage(navOptions: NavOptions? = null) {
    this.navigate(myPageNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.myPageScreen() {
    composable(route = myPageNavigationRoute) {
        MyPageRoute()
    }
}