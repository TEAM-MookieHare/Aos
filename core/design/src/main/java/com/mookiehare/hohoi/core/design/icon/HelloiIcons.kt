package com.mookiehare.hohoi.core.design.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

object HelloiIcons {
    val MoreVert = Icons.Default.MoreVert
    val Map = Icons.Default.Map
    val QuestionMark = Icons.Default.QuestionMark
    val Chat = Icons.Default.ChatBubble
    val MyPage = Icons.Default.Person
    val Person = Icons.Default.Person
}

/**
 * [ImageVector], [DrawableRes] icon 들을 쉽게 다루기 위한 sealed class 입니다.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}