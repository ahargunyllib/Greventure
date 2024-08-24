package com.seven_sheesh.greventure.presentation.ui.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.view.bookmark.BookmarkScreen
import com.seven_sheesh.greventure.presentation.view.home.HomeScreen
import com.seven_sheesh.greventure.presentation.view.maps.MapsScreen
import com.seven_sheesh.greventure.presentation.view.news.NewsScreen
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@Composable
fun HomeNavHost(parentNavController: NavController = rememberNavController()){
    val homeNavController = rememberNavController()
    val navbarViewModel   = hiltViewModel<NavbarViewModel>()

    NavHost(navController = homeNavController, startDestination = HomeNavObj.HomeScreen.route, builder = {
        composable(
            route = HomeNavObj.HomeScreen.route,
            content = {
                HomeScreen(
                    homeNavController = homeNavController,
                    navbarViewModel = navbarViewModel
                )
            }
        )

        composable(
            route = HomeNavObj.MapsScreen.route,
            content = {
                MapsScreen(
                    homeNavController = homeNavController,
                    navbarViewModel = navbarViewModel
                )
            }
        )

        composable(
            route = HomeNavObj.BookmarkScreen.route,
            content = {
                BookmarkScreen(
                    homeNavController = homeNavController,
                    navbarViewModel = navbarViewModel
                )
            }
        )

        composable(
            route = HomeNavObj.NewsScreen.route,
            content = {
                NewsScreen(
                    homeNavController = homeNavController,
                    navbarViewModel = navbarViewModel
                )
            }
        )
    })
}