package com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj

sealed class AuthNavObj(val route: String) {
    data object Splash: AuthNavObj("splash")
    data object Boarding1: AuthNavObj("boarding-1")
    data object Boarding2: AuthNavObj("boarding-2")
    data object Boarding3: AuthNavObj("boarding-3")
    data object Login: AuthNavObj("login")
    data object Register: AuthNavObj("register")
    data object Home: AuthNavObj("home")
}