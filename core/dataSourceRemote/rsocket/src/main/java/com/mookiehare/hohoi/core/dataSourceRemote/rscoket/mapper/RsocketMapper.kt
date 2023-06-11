package com.mookiehare.hohoi.core.dataSourceRemote.rscoket.mapper

import com.mookiehare.hohoi.core.common.model.ConnectState
import hare.rsocket.client.RsocketStatus

fun RsocketStatus.toConnectStatus(): com.mookiehare.hohoi.core.common.model.ConnectState = when(this){
    RsocketStatus.Connect -> com.mookiehare.hohoi.core.common.model.ConnectState.Connect
    RsocketStatus.DisConnect -> com.mookiehare.hohoi.core.common.model.ConnectState.DisConnect
    is RsocketStatus.Error -> com.mookiehare.hohoi.core.common.model.ConnectState.Error(this.exception)
    RsocketStatus.Loading -> com.mookiehare.hohoi.core.common.model.ConnectState.Loading
}