package com.mookiehare.hohoi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mookiehare.hohoi.core.design.component.HoHoiNavigationBar
import com.mookiehare.hohoi.core.design.component.HoHoiNavigationBarItem
import com.mookiehare.hohoi.core.design.component.HoHoiTopAppBar
import com.mookiehare.hohoi.core.design.icon.Icon.DrawableResourceIcon
import com.mookiehare.hohoi.core.design.icon.Icon.ImageVectorIcon
import com.mookiehare.hohoi.navigation.HoHoiNavHost
import com.mookiehare.hohoi.navigation.TopLevelDestination

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
)
@Composable
fun HoHoiApp(
    windowSizeClass: WindowSizeClass,
    appState: HoHoiAppState = rememberHoHoiAppState(
        windowSizeClass = windowSizeClass
    )
) {
    Scaffold(
        //https://developer.android.com/jetpack/compose/testing 참고
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            // Show the top app bar on top level destinations.
            val destination = appState.currentTopLevelDestination
            if (destination != null) {
                HoHoiTopAppBar(
                    // 탐색 레일이 표시되면 상단 앱 모음이 기본적으로 중첩됩니다.
                    // 즉, 네비게이션 레일에서 가장 위에 있는 항목을 사용할 수 없다는 것을 의미합니다.
                    // 해결 방법은 zIndex를 사용하여 탐색 레일 뒤에 상단 앱 모음을 배치하는 것입니다. (Rail 사용시)
                    modifier = Modifier.zIndex(-1F),
                    titleRes = destination.titleTextId,
                    actions = listOf()
                )
            }
        },
        bottomBar = {
            HoHoiBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        }
    ) { padding ->

        Row(
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            HoHoiNavHost(
                navController = appState.navController,
                onBackClick = appState::onBackClick,

                modifier = Modifier
                    .padding(padding)
                    .consumedWindowInsets(padding)
            )
        }

        // TODO: 스낵바가 표시될 때 content 가 뒤에 표시되지 않도록 padding이나 spacer를 추가할 수 있습니다.
    }
}

@Composable
fun HoHoiBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    HoHoiNavigationBar {
        destinations.forEach{ destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            HoHoiNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )

                        is DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(stringResource(destination.iconTextId)) }

            )

        }

    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false