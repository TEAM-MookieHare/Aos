package com.mookiehare.hohoi.feature.mapmatching.model

import androidx.compose.runtime.MutableState

data class RandomChip (
    val text : String = "",
    val isSelected : MutableState<Boolean>
    )