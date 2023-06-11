package com.mookiehare.hohoi.core.dataSourceRemote.rscoket

import com.mookiehare.hohoi.core.data.dataSource.chat.ChatDataSourceRemote
import com.mookiehare.hohoi.core.data.dataSource.chat.model.ChatRoomDTO
import com.mookiehare.hohoi.core.common.model.ConnectState
import com.mookiehare.hohoi.core.data.dataSource.chat.model.CreateChatRoomDTO
import com.mookiehare.hohoi.core.data.dataSource.chat.model.JoinChatRoomDTO
import com.mookiehare.hohoi.core.data.dataSource.chat.model.MessageDTO
import com.mookiehare.hohoi.core.dataSourceRemote.rscoket.mapper.toConnectStatus
import hare.rsocket.client.RsocketClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RsocketChatDataSourceRemoteImpl @Inject constructor(
    private val rsocketClient: RsocketClient,
    private val rsocketChatApi: RsocketChatApi,
): ChatDataSourceRemote {
    override fun connectStatus(): Flow<com.mookiehare.hohoi.core.common.model.ConnectState> {
        return rsocketClient.status.map { it.toConnectStatus() }
    }

    override suspend fun connect() {
        rsocketClient.connect(
            address = BuildConfig.RSOCKET_ADDRESS,
            port = BuildConfig.RSOCKET_PORT.toInt(),
            path = BuildConfig.RSOCKET_PATH
        )
    }

    override fun messageStream(memberId: Long): Flow<MessageDTO> {
        return rsocketClient.requestStream(rsocketChatApi.messageStream(memberId))
            .map { Json.decodeFromString(it.data.readText()) }
    }

    override suspend fun sendMessage(messageStream: MessageDTO) {
        rsocketClient.requestResponse(
            rsocketChatApi.sendMessage(messageStream.chatRoomId),
            Json.encodeToString(messageStream)
        )
    }

    override fun joinChatRoom(joinChatRoomDTO: JoinChatRoomDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun createChatRoom(createChatRoomDTO: CreateChatRoomDTO) {
        rsocketClient.requestResponse(
            rsocketChatApi.createChatRoom(),
            Json.encodeToString(CreateChatRoomDTO)
        )
    }

    override fun chatRoomStream(memberId: Long): Flow<ChatRoomDTO>{
        TODO("Not yet implemented")
    }
}

