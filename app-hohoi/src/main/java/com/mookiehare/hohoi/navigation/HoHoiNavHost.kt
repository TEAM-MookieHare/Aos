package com.mookiehare.hohoi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mookiehare.hohoi.feature.chat.navigation.chatScreen
import com.mookiehare.hohoi.feature.mapmatching.navigation.mapMatchingScreen
import com.mookiehare.hohoi.feature.mypage.navigation.myPageScreen
import com.mookiehare.hohoi.feature.randommatching.navigation.randomMatchingNavigationRoute
import com.mookiehare.hohoi.feature.randommatching.navigation.randomMatchingScreen

//최상위 탐색 그래프입니다.
// 내비게이션은 https://d.android.com/jetpack/compose/nav-adaptive에서 설명하는 대로 구성됩니다.
// 이 파일에 정의된 탐색 그래프는 다양한 최상위 경로를 정의합니다. 각 경로 내의 탐색은 state 및 Back Handlers 를 사용하여 처리됩니다.
@Composable
fun HoHoiNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = randomMatchingNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ){
        randomMatchingScreen()
        chatScreen()
        mapMatchingScreen()
        myPageScreen()
    }
}