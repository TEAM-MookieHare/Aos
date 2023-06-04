package com.mookiehare.hohoi.core.dataSourceRemote.rscoket

class RsocketChatApi(){
    fun sendMessage(chatRoomId: Long) = "chatRoom/${chatRoomId}/message"
    fun messageStream(memberId: Long) = "member/${memberId}/message/stream"
    fun createChatRoom() = "/chatRoom/create"
    fun chatRoomStream(memberId: Long) = "/chatRoom/${memberId}/stream"
}
