package com.mookiehare.hohoi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.mookiehare.hohoi.ui.HoHoiApp
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HohoiTheme {

                HoHoiApp(
                    windowSizeClass = calculateWindowSizeClass(this),
                )
            }
        }
    }
}

