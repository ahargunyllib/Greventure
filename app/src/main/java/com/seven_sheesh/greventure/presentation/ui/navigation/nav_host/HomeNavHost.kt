package com.seven_sheesh.greventure.presentation.ui.navigation.nav_host

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.Navbar
import com.seven_sheesh.greventure.presentation.view.bookmark.BookmarkScreen
import com.seven_sheesh.greventure.presentation.view.home.CityScreen
import com.seven_sheesh.greventure.presentation.view.home.EventScreen
import com.seven_sheesh.greventure.presentation.view.home.HomeScreen
import com.seven_sheesh.greventure.presentation.view.home.NewsScreen
import com.seven_sheesh.greventure.presentation.view.home.NotificationScreen
import com.seven_sheesh.greventure.presentation.view.maps.DetailScreen
import com.seven_sheesh.greventure.presentation.view.maps.DiscussionScreen
import com.seven_sheesh.greventure.presentation.view.maps.MapsScreen
import com.seven_sheesh.greventure.presentation.view.profile.EditProfileScreen
import com.seven_sheesh.greventure.presentation.view.profile.ProfileScreen
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun HomeNavHost(parentNavController: NavController = rememberNavController()){
    val homeNavController = rememberNavController()
    val navbarViewModel   = hiltViewModel<NavbarViewModel>()
    val mapsViewModel     = hiltViewModel<MapsViewModel>()

    Scaffold(
        containerColor = GreventureScheme.White.color,
        contentColor = GreventureScheme.White.color,
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
                route = HomeNavObj.CityScreen.route,
                content = {
                    CityScreen(
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
                route = HomeNavObj.NotificationScreen.route,
                content = {
                    NotificationScreen(
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
                route = HomeNavObj.EventScreen.route,
                content = {
                    EventScreen(
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
                route = HomeNavObj.NewsScreen.route,
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

            composable(
                route = HomeNavObj.MapsScreen.route,
                content = {
                    MapsScreen(
                        homeNavController = homeNavController,
                        navbarViewModel = navbarViewModel,
                        mapsViewModel = mapsViewModel
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
                route = HomeNavObj.DetailScreen.route,
                content = {
                    DetailScreen(
                        homeNavController = homeNavController,
                        navbarViewModel = navbarViewModel,
                        mapsViewModel = mapsViewModel
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
                route = HomeNavObj.DiscussionScreen.route,
                content = {
                    DiscussionScreen(
//                        homeNavController = homeNavController,
//                        navbarViewModel = navbarViewModel,
//                        mapsViewModel = mapsViewModel
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
                    ProfileScreen(
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
                route = HomeNavObj.EditProfileScreen.route,
                content = {
                    EditProfileScreen(
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