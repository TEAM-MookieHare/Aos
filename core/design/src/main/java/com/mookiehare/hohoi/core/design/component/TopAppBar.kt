package com.mookiehare.hohoi.core.design.component

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mookiehare.hohoi.core.design.icon.HelloiIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoHoiTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    actions: List<TopAppBarAction>
){
    TopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        actions = {
            actions.forEach{ topAppBarAction ->
                IconButton(onClick = topAppBarAction.onActionClick) {
                    Icon(
                        imageVector = topAppBarAction.actionIcon,
                        contentDescription = topAppBarAction.actionIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
fun HoHoiTopAppBarPreview(){
    HoHoiTopAppBar(
        titleRes = android.R.string.unknownName,
        actions = listOf(
            TopAppBarAction(
                actionIcon = HelloiIcons.MoreVert,
            ),
            TopAppBarAction(
                actionIcon = HelloiIcons.MoreVert,
            ),
            TopAppBarAction(
                actionIcon = HelloiIcons.MoreVert,
            )
        )
    )
}

data class TopAppBarAction(
    val actionIcon: ImageVector,
    val actionIconContentDescription: String = "",
    val onActionClick: () -> Unit = {}
)