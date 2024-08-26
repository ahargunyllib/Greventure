package com.seven_sheesh.greventure

import android.annotation.SuppressLint
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
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.design_system.Theme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_host.HomeNavHost
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_host.ParentNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
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
                            modifier = Modifier
                                .fillMaxSize()
                                .background(GreventureScheme.White.color),
                            content = { _ -> ParentNavHost() },
                        )
                    },
                )
            }
        )
    }
}