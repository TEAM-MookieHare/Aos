package com.mookiehare.hohoi.feature.chat.model

sealed class Message()

sealed class FromMy(): Message()

sealed class FromOther(
    open val nickname: String,
    open val profileUrl: String?,
): Message()

data class MyText(
    val id: String,
    val content: String,
    val isDelete: Boolean,
    val isReport: Boolean,
    val sendTime: String,
): FromMy(
)

data class OtherText(
    val id: String,
    override val nickname: String,
    override val profileUrl: String?,
    val content: String,
    val isDelete: Boolean,
    val isReport: Boolean,
    val sendTime: String,
): FromOther(
    nickname = nickname,
    profileUrl = profileUrl
)