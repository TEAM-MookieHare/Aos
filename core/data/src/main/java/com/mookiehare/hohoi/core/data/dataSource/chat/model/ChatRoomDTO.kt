package com.mookiehare.hohoi.core.data.dataSource.chat.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomDTO(
    val id: Long?,
    val createdAt: String?,
    val updatedAt: String?,
    val title: String,
    val lastMessage: String?
)