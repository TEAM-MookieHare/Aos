package com.mookiehare.hohoi.feature.chat.model

data class Chat(
    val id: String,
    val nickname: String,
    val content: String,
    val receivedTime: String,
    val profile: String?,
)
