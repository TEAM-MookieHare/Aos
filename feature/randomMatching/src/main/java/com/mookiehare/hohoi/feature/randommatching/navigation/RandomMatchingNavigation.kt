package com.mookiehare.hohoi.feature.randommatching.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mookiehare.hohoi.feature.randommatching.RandomMatchingRoute

const val randomMatchingNavigationRoute = "random_matching_route"

fun NavController.navigateToRandomMatching(navOptions: NavOptions? = null) {
    this.navigate(randomMatchingNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.randomMatchingScreen() {
    composable(route = randomMatchingNavigationRoute) {
        RandomMatchingRoute()
    }
}