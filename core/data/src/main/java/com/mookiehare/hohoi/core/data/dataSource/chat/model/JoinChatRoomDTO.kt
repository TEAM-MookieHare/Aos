package com.mookiehare.hohoi.core.data.dataSource.chat.model

import kotlinx.serialization.Serializable

@Serializable
data class JoinChatRoomDTO(
    val chatRoomId: Long,
    val memberId: Long,
)