package com.mookiehare.hohoi.feature.randommatching.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val randomMatchingNavigationRoute = "random_matching_route"

fun NavController.navigateToRandomMatching(navOptions: NavOptions? = null) {
    this.navigate(randomMatchingNavigationRoute, navOptions)
}

fun NavGraphBuilder.randomMatchingScreen() {
    composable(route = randomMatchingNavigationRoute) {

    }
}