package com.seven_sheesh.greventure.presentation.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@Composable
@Preview
fun Navbar(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel()
){
    val currentPage by navbarViewModel.pageState.collectAsState()

    BottomAppBar(
        containerColor = Color.LightGray,
        modifier = Modifier.height(96.dp),
        tonalElevation = 16.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navbarViewModel.setPageState(0)
                homeNavController.navigate(HomeNavObj.HomeScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = HomeNavObj.HomeScreen.route)
            }
            IconButton(onClick = {
                navbarViewModel.setPageState(1)
                homeNavController.navigate(HomeNavObj.MapsScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Map, contentDescription = HomeNavObj.MapsScreen.route)
            }
            IconButton(onClick = {
                navbarViewModel.setPageState(2)
                homeNavController.navigate(HomeNavObj.BookmarkScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Bookmark, contentDescription = HomeNavObj.BookmarkScreen.route)
            }
            IconButton(onClick = {
                navbarViewModel.setPageState(3)
                homeNavController.navigate(HomeNavObj.ProfileScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = HomeNavObj.ProfileScreen.route)
            }
        }
    }
}