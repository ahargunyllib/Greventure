package com.seven_sheesh.greventure.presentation.ui.navigation.nav_host

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.AuthNavObj
import com.seven_sheesh.greventure.presentation.view.auth.Boarding1Screen
import com.seven_sheesh.greventure.presentation.view.auth.Boarding2Screen
import com.seven_sheesh.greventure.presentation.view.auth.Boarding3Screen
import com.seven_sheesh.greventure.presentation.view.auth.LoginScreen
import com.seven_sheesh.greventure.presentation.view.auth.RegisterScreen
import com.seven_sheesh.greventure.presentation.view.auth.SplashScreen

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AuthNavHost(
    parentNavController: NavController = rememberNavController()
) {
    val authNavCtr = rememberNavController()

    NavHost(navController = authNavCtr, startDestination = AuthNavObj.Splash.route) {
        composable(AuthNavObj.Splash.route) {
            SplashScreen(authNavCtr = authNavCtr, parentNavCtr = parentNavController)
        }

        composable(AuthNavObj.Boarding1.route) {
            Boarding1Screen(authNavCtr = authNavCtr)
        }

        composable(AuthNavObj.Boarding2.route) {
            Boarding2Screen(authNavCtr = authNavCtr)
        }

        composable(AuthNavObj.Boarding3.route) {
            Boarding3Screen(authNavCtr = authNavCtr, parentNavCtr = parentNavController)
        }

        composable(AuthNavObj.Login.route) {
            LoginScreen(authNavCtr = authNavCtr, parentNavCtr = parentNavController)
        }

        composable(AuthNavObj.Register.route) {
            RegisterScreen(authNavCtr = authNavCtr)
        }
    }

}