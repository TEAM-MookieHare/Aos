package com.mookiehare.hohoi.navigation

import com.mookiehare.hohoi.core.design.icon.HoHoiIcons
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
        selectedIcon = ImageVectorIcon(HoHoiIcons.QuestionMark),
        unselectedIcon = ImageVectorIcon(HoHoiIcons.QuestionMark),
        iconTextId = randommatchingR.string.random,
        titleTextId = randommatchingR.string.random
    ),
    CHAT(
        selectedIcon = ImageVectorIcon(HoHoiIcons.Chat),
        unselectedIcon = ImageVectorIcon(HoHoiIcons.Chat),
        iconTextId = chatR.string.chat,
        titleTextId = chatR.string.chat
    ),
    MAP_MATCHING(
        selectedIcon = ImageVectorIcon(HoHoiIcons.Map),
        unselectedIcon = ImageVectorIcon(HoHoiIcons.Map),
        iconTextId = mapmatchingR.string.map,
        titleTextId = mapmatchingR.string.map
    ),
    MY_PAGE(
        selectedIcon = ImageVectorIcon(HoHoiIcons.MyPage),
        unselectedIcon = ImageVectorIcon(HoHoiIcons.MyPage),
        iconTextId = mypageR.string.my_page,
        titleTextId = mypageR.string.my_page
    ),
}