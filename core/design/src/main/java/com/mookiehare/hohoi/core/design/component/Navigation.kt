package com.mookiehare.hohoi.core.design.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * HoHoi navigation bar 는 Material 3 [NavigationBar]을 Wraps 하여 만들었으며 content slot 입니다.
 *
 * @param modifier 는 navigation bar 에 Modifier 를 적용 할 수 있습니다.
 * @param content 는 navigation bar 의 Destinations 요소들 이며 여러개의 [NavigationBarItem]로 구성합니다.
 */
@Composable
fun HoHoiNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        contentColor = HoHoiNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

/**
 * HoHoi navigation bar item 은 icon 과 label content slots 이며
 * Material 3 [NavigationBarItem] Wraps 하여 만들었습니다.
 *
 * @param selected 는 아이템 selected 유무.
 * @param onClick 는 item selected 시 실행할 콜백 함수.
 * @param icon 는 item icon content.
 * @param modifier 는 item 에 Modifier 를 적용할 수 있습니다.
 * @param selectedIcon 는 selected 되었을 시 The item icon content.
 * @param enabled 는 item enabled state 를 control 할 수 있습니다. `false`일 경우 item 은 클릭 할 수 없으며 비활성화 됩니다.
 * @param label 는 item text label content.
 * @param alwaysShowLabel 는 item 에 항상 label을 보여줄 지 유무 입니다. `false`일 경우 label 은 item 이 selected 되었을때만 보여집니다.
 */
@Composable
fun RowScope.HoHoiNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = HoHoiNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = HoHoiNavigationDefaults.navigationContentColor(),
            selectedTextColor = HoHoiNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = HoHoiNavigationDefaults.navigationContentColor(),
            indicatorColor = HoHoiNavigationDefaults.navigationIndicatorColor()
        )
    )
}

/**
 * HoHoi navigation default values.
 */
object HoHoiNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant
    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer
    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}