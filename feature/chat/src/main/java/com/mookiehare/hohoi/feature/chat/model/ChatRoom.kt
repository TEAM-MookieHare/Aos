package com.mookiehare.hohoi.feature.chat.model

data class ChatRoom(
    val id: String,
    val nickname: String,
    val content: String,
    val receivedTime: String,
    val profile: String?,
)
