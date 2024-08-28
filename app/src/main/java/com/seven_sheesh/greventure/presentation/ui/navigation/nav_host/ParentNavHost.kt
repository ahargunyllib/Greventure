package com.seven_sheesh.greventure.presentation.ui.navigation.nav_host

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun ParentNavHost() {
    val parentNavCtr = rememberNavController()

    NavHost(
        navController = parentNavCtr,
        startDestination = ParentNavObj.Auth.route,
        builder = {
            composable(ParentNavObj.Auth.route) {
                AuthNavHost(parentNavCtr = parentNavCtr)
            }
        },
    )
}