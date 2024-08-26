package com.seven_sheesh.greventure.presentation.ui.navigation.nav_host

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj
import com.seven_sheesh.greventure.presentation.view.auth.SignInScreen
import com.seven_sheesh.greventure.presentation.view.boarding.BoardingScreen0
import com.seven_sheesh.greventure.presentation.view.boarding.BoardingScreen1
import com.seven_sheesh.greventure.presentation.view.boarding.BoardingScreen2
import com.seven_sheesh.greventure.presentation.view.boarding.BoardingScreen3

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun ParentNavHost() {
    val parentNavController = rememberNavController()

    NavHost(
        navController = parentNavController,
        startDestination = ParentNavObj.BoardingScreen0.route,
        builder = {
            composable(
                route = ParentNavObj.BoardingScreen0.route,
                content = {
                    BoardingScreen0(
                        parentNavController = parentNavController
                    )
                },
            )

            composable(
                route = ParentNavObj.BoardingScreen1.route,
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, tween(700)
                    )
                },
                content = {
                    BoardingScreen1(
                        parentNavController = parentNavController
                    )
                },
            )

            composable(
                route = ParentNavObj.BoardingScreen2.route,
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, tween(700)
                    )
                },
                content = {
                    BoardingScreen2(
                        parentNavController = parentNavController
                    )
                },
            )

            composable(
                route = ParentNavObj.BoardingScreen3.route,
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, tween(700)
                    )
                },
                content = {
                    BoardingScreen3(
                        parentNavController = parentNavController
                    )
                },
            )

            composable(
                route = ParentNavObj.LoginScreen.route,
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, tween(700)
                    )
                },
                content = {
                    SignInScreen(
                        navController = parentNavController
                    )
                }
            )

            composable(
                route = ParentNavObj.HomeNav.route,
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, tween(700)
                    )
                }, popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, tween(700)
                    )
                },
                content = {
                    HomeNavHost(
                        parentNavController = parentNavController
                    )
                }
            )
        },
    )
}