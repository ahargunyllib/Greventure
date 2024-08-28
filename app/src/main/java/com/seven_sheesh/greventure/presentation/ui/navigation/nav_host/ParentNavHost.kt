package com.seven_sheesh.greventure.presentation.ui.navigation.nav_host

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun ParentNavHost() {
    val parentNavController = rememberNavController()

    NavHost(
        navController = parentNavController,
        startDestination = ParentNavObj.Auth.route,
        builder = {
            composable(ParentNavObj.Auth.route) {
                AuthNavHost(parentNavCtr = parentNavController)
            }

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