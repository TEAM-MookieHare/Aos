package com.mookiehare.hohoi.core.data.dataSource.chat.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateChatRoomDTO(
    val chatRoomMemberIds: List<Long>,
    val lastMessage: String,
    val title: String? = null,
    val messageType: String,
    val senderMemberId: Long,
)