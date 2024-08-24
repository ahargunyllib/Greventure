package com.ahargunyllib.hackathon_kotlin_starter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ahargunyllib.hackathon_kotlin_starter.presentation.ui.design_system.Theme
import com.ahargunyllib.hackathon_kotlin_starter.presentation.ui.navigation.nav_host.ParentNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent(
            content = {
                Theme(
                    isDarkTheme = false,
                    dynamicColor = false,
                    content = {
                        Scaffold(
                            modifier = Modifier.fillMaxSize().background(Color.White),
                            content = { _ -> ParentNavHost() },
                        )
                    },
                )
            }
        )
    }
}