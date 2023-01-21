package com.mookiehare.hohoi.feature.chat.model

sealed class Message()

data class MyTextMessage(
    val id: String,
    val content: String,
    val isDelete: Boolean,
    val isReport: Boolean,
    val sendTime: String,
): Message()

data class OtherTextMessage(
    val id: String,
    val nickname: String,
    val profileUrl: String?,
    val content: String,
    val isDelete: Boolean,
    val isReport: Boolean,
    val sendTime: String,
): Message()