package com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj

sealed class ParentNavObj (val route: String){
    data object Auth: ParentNavObj("auth")
    data object HomeNav: ParentNavObj("home-nav")
}