package com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj

sealed class HomeNavObj(val route: String) {
    data object HomeScreen: HomeNavObj("home")
    data object MapsScreen: HomeNavObj("maps")
    data object BookmarkScreen: HomeNavObj("bookmark")
    data object ProfileScreen: HomeNavObj("profile")
}