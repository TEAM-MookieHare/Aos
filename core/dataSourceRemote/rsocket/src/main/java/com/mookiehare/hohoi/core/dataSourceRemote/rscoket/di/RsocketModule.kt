package com.mookiehare.hohoi.core.dataSourceRemote.rscoket.di

import com.mookiehare.hohoi.core.dataSourceRemote.rscoket.RsocketChatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hare.rsocket.client.RsocketClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.rsocket.kotlin.core.WellKnownMimeType
import io.rsocket.kotlin.keepalive.KeepAlive
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.payload.PayloadMimeType
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(SingletonComponent::class)
object RsocketModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient = HttpClient(CIO) {
        install(WebSockets)
        install(RSocketSupport){
            connector {

                connectionConfig {

                    keepAlive = KeepAlive(
                        interval = 30.seconds,
                        maxLifetime = 2.minutes
                    )

                    payloadMimeType = PayloadMimeType(
                        data = WellKnownMimeType.ApplicationJson,
                        metadata = WellKnownMimeType.MessageRSocketRouting
                    )
                }
            }
        }
    }

    @Provides
    @Singleton
    fun providesRsocket(httpClient: HttpClient) = RsocketClient(httpClient)

    @Provides
    @Singleton
    fun providesRsocketChatApi() = RsocketChatApi()
}