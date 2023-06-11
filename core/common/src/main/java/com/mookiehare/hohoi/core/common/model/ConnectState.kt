package com.mookiehare.hohoi.core.common.model

sealed class ConnectState {
    object Loading: ConnectState()
    object Connect: ConnectState()
    object DisConnect: ConnectState()
    data class Error(val exception: Throwable?): ConnectState()
}