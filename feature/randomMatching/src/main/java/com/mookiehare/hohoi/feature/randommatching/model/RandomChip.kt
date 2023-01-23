package com.mookiehare.hohoi.feature.randommatching.model

import androidx.compose.runtime.MutableState

data class RandomChip (
    val text : String = "",
    val isSelected : MutableState<Boolean>
    )