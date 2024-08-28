package com.seven_sheesh.greventure.presentation.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.AuthNavObj
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    authNavCtr: NavController
) {
    LaunchedEffect(Unit) {
        delay(2500)
        authNavCtr.navigate(AuthNavObj.Boarding1.route)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = GreventureScheme.White.color,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Greventure Logo",
            )
        }
    }
}