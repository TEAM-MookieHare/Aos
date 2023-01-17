package com.mookiehare.hohoi.navigation

import com.mookiehare.hohoi.core.design.icon.HelloiIcons
import com.mookiehare.hohoi.core.design.icon.Icon
import com.mookiehare.hohoi.core.design.icon.Icon.ImageVectorIcon
import com.mookiehare.hohoi.feature.randommatching.R as randommatchingR
import com.mookiehare.hohoi.feature.chat.R as chatR
import com.mookiehare.hohoi.feature.mapmatching.R as mapmatchingR
import com.mookiehare.hohoi.feature.mypage.R as mypageR

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    RANDOM_MATCHING(
        selectedIcon = ImageVectorIcon(HelloiIcons.QuestionMark),
        unselectedIcon = ImageVectorIcon(HelloiIcons.QuestionMark),
        iconTextId = randommatchingR.string.random,
        titleTextId = randommatchingR.string.random
    ),
    CHAT(
        selectedIcon = ImageVectorIcon(HelloiIcons.Chat),
        unselectedIcon = ImageVectorIcon(HelloiIcons.Chat),
        iconTextId = chatR.string.chat,
        titleTextId = chatR.string.chat
    ),
    MAP_MATCHING(
        selectedIcon = ImageVectorIcon(HelloiIcons.Map),
        unselectedIcon = ImageVectorIcon(HelloiIcons.Map),
        iconTextId = mapmatchingR.string.map,
        titleTextId = mapmatchingR.string.map
    ),
    MY_PAGE(
        selectedIcon = ImageVectorIcon(HelloiIcons.MyPage),
        unselectedIcon = ImageVectorIcon(HelloiIcons.MyPage),
        iconTextId = mypageR.string.my_page,
        titleTextId = mypageR.string.my_page
    ),
}