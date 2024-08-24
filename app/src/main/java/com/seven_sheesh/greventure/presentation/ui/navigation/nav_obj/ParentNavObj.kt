package com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj

sealed class ParentNavObj (val route: String){
    data object SplashScreen0 : ParentNavObj("splash-screen-0")
    data object LoginScreen : ParentNavObj("login")
}