package com.mookiehare.hohoi.feature.chat.chatDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mookiehare.hohoi.feature.chat.navigation.ChatDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val chatDetailArgs: ChatDetailArgs = ChatDetailArgs(savedStateHandle)

    val chatDetailId: StateFlow<String> = MutableStateFlow(chatDetailArgs.chatDetailId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = chatDetailArgs.chatDetailId
        )
}