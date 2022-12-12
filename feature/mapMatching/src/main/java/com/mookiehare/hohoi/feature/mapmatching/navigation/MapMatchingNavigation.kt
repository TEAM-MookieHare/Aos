package com.mookiehare.hohoi.feature.mapmatching.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val mapMatchingNavigationRoute = "map_matching_route"

fun NavController.navigateToMapMatching(navOptions: NavOptions? = null) {
    this.navigate(mapMatchingNavigationRoute, navOptions)
}

fun NavGraphBuilder.mapMatchingScreen() {
    composable(route = mapMatchingNavigationRoute) {

    }
}