package com.mookiehare.hohoi.feature.chat.model

data class Chat(
    val nickname: String,
    val content: String,
    val receivedTime: String,
    val profile: String?,
)
