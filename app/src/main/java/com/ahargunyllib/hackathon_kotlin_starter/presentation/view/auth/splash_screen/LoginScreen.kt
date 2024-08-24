package com.ahargunyllib.hackathon_kotlin_starter.presentation.view.auth.splash_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ahargunyllib.hackathon_kotlin_starter.presentation.ui.navigation.nav_obj.ParentNavObj

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Box(
        content = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
                content = {
                    Text("Login")
                }
            )
        }
    )
}