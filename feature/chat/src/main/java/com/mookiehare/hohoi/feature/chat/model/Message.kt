package com.mookiehare.hohoi.feature.chat.model

sealed class Message(
    open val id: String,
    open val sendTime: String,
    open val from: From
)

sealed class From{
    object My : From()

    data class Other(
        val nickname: String,
        val profileUrl: String,
    ): From()
}

data class Text(
    override val id: String,
    override val sendTime: String,
    override val from: From,
    val content: String,
): Message(
    id = id,
    sendTime = sendTime,
    from = from
)

data class ReplyText(
    override val id: String,
    override val sendTime: String,
    override val from: From,
    val replyId: String,
    val replyNickname: String,
    val replyContent: String,
    val content: String,
): Message(
    id = id,
    sendTime = sendTime,
    from = from
)