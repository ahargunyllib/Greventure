package com.seven_sheesh.greventure.presentation.view.auth.splash_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    parentNavController: NavController = rememberNavController()
) {
    Box(
        content = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
                content = {
                    Text("Login Screen")
                    Button(
                        onClick = {
                            parentNavController.navigate(ParentNavObj.HomeNav.route)
                        }
                    ){
                        Text("Login")
                    }
                }
            )
        }
    )
}