package com.mookiehare.hohoi.core.data.dataSource.chat

import com.mookiehare.hohoi.core.data.dataSource.chat.model.ChatRoomDTO
import com.mookiehare.hohoi.core.common.model.ConnectState
import com.mookiehare.hohoi.core.data.dataSource.chat.model.CreateChatRoomDTO
import com.mookiehare.hohoi.core.data.dataSource.chat.model.JoinChatRoomDTO
import com.mookiehare.hohoi.core.data.dataSource.chat.model.MessageDTO
import kotlinx.coroutines.flow.Flow

interface ChatDataSourceRemote {
    fun connectStatus(): Flow<com.mookiehare.hohoi.core.common.model.ConnectState>
    suspend fun connect()
    fun messageStream(memberId: Long): Flow<MessageDTO>
    suspend fun sendMessage(message: MessageDTO)
    fun joinChatRoom(joinChatRoomDTO: JoinChatRoomDTO)
    suspend fun createChatRoom(createChatRoomDTO: CreateChatRoomDTO)
    fun chatRoomStream(memberId: Long): Flow<ChatRoomDTO>
}