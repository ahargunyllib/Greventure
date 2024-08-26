package com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj

sealed class ParentNavObj (val route: String){
    data object BoardingScreen0 : ParentNavObj("boarding-screen-0")
    data object BoardingScreen1 : ParentNavObj("boarding-screen-1")
    data object BoardingScreen2 : ParentNavObj("boarding-screen-2")
    data object BoardingScreen3 : ParentNavObj("boarding-screen-3")
    data object LoginScreen : ParentNavObj("login")
    data object HomeNav: ParentNavObj("home-nav")
}