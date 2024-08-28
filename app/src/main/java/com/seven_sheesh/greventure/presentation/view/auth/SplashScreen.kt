package com.seven_sheesh.greventure.presentation.view.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.AuthNavObj
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj
import com.seven_sheesh.greventure.presentation.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    authNavCtr: NavController,
    parentNavCtr: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val user = profileViewModel.user.collectAsState(initial = Pair("", null)).value.second

    LaunchedEffect(user) {
        delay(5000)
        if (user?.email != null) {
            parentNavCtr.navigate(ParentNavObj.HomeNav.route)
        } else {
            authNavCtr.navigate(AuthNavObj.Boarding1.route)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = GreventureScheme.White.color,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Greventure Logo",
            )
//            Text(user?.email ?: "")
        }
    }
}