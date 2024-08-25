package com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj

sealed class HomeNavObj(val route: String) {
    data object HomeScreen: HomeNavObj("home")
    data object CityScreen: HomeNavObj("home-city")
    data object NotificationScreen: HomeNavObj("home-notification")
    data object EventScreen: HomeNavObj("home-event")
    data object NewsScreen: HomeNavObj("home-news")
    data object MapsScreen: HomeNavObj("maps")
    data object DetailScreen: HomeNavObj("maps-detail")
    data object DiscussionScreen: HomeNavObj("maps-discussion")
    data object BookmarkScreen: HomeNavObj("bookmark")
    data object ProfileScreen: HomeNavObj("profile")
}