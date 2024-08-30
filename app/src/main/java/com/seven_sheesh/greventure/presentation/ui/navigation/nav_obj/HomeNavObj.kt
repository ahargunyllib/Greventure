package com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj

sealed class HomeNavObj(val route: String) {
    data object HomeScreen: HomeNavObj("home")
    data object CityScreen: HomeNavObj("home-city")
    data object NotificationScreen: HomeNavObj("home-notification")
    data object EventScreen: HomeNavObj("home-event")
    data object NewsScreen: HomeNavObj("home-news")
    data object NewsDetailScreen: HomeNavObj("home-news-detail")
    data object MapsScreen: HomeNavObj("maps")
    data object DetailScreen: HomeNavObj("maps-detail/{bubble_id}"){
        fun createRoute(bubbleId: String) = "maps-detail/$bubbleId"
    }
    data object DiscussionScreen: HomeNavObj("maps-discussion")
    data object BookmarkScreen: HomeNavObj("bookmark")
    data object ProfileScreen: HomeNavObj("profile")
    data object EditProfileScreen: HomeNavObj("profile-edit")
    data object YourBubbleScreen: HomeNavObj("profile-bubble")
    data object CreateBubble1: HomeNavObj("profile-create-bubble1")
    data object CreateBubble2: HomeNavObj("profile-create-bubble2")
    data object CreateBubble3: HomeNavObj("profile-create-bubble3")
    data object CreateBubblePick: HomeNavObj("profile-create-bubble-pick")
}