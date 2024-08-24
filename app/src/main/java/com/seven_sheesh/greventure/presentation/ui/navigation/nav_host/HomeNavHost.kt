package com.seven_sheesh.greventure.presentation.ui.navigation.nav_host

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.Navbar
import com.seven_sheesh.greventure.presentation.view.bookmark.BookmarkScreen
import com.seven_sheesh.greventure.presentation.view.home.HomeScreen
import com.seven_sheesh.greventure.presentation.view.maps.MapsScreen
import com.seven_sheesh.greventure.presentation.view.profile.NewsScreen
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeNavHost(parentNavController: NavController = rememberNavController()){
    val homeNavController = rememberNavController()
    val navbarViewModel   = hiltViewModel<NavbarViewModel>()

    Scaffold(
        bottomBar = {
            Navbar(
                homeNavController = homeNavController,
                navbarViewModel = navbarViewModel
            )
        }
    ) {
        NavHost(navController = homeNavController, startDestination = HomeNavObj.HomeScreen.route, builder = {
            composable(
                route = HomeNavObj.HomeScreen.route,
                content = {
                    HomeScreen(
                        homeNavController = homeNavController,
                        navbarViewModel = navbarViewModel
                    )
                }, enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down, tween(700)
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
                }, enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down, tween(700)
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
                }, enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down, tween(700)
                    )
                }
            )

            composable(
                route = HomeNavObj.ProfileScreen.route,
                content = {
                    NewsScreen(
                        homeNavController = homeNavController,
                        navbarViewModel = navbarViewModel
                    )
                }, enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down, tween(700)
                    )
                }
            )
        })
    }
}