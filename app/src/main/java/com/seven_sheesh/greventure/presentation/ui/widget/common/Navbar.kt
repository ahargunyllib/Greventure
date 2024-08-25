package com.seven_sheesh.greventure.presentation.ui.widget.common

import android.widget.Toast
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
import androidx.compose.ui.draw.shadow
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
        containerColor = Color.White,
        modifier = Modifier.height(96.dp).shadow(32.dp),
        tonalElevation = 32.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if(currentPage != 0){
                    navbarViewModel.setPageState(0)
                    homeNavController.navigate(HomeNavObj.HomeScreen.route)
                } else {
                    Toast.makeText(homeNavController.context, "Home", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = HomeNavObj.HomeScreen.route, tint = isHighLighted(currentPage, 0))
            }
            IconButton(onClick = {
                if(currentPage != 1){
                    navbarViewModel.setPageState(1)
                    homeNavController.navigate(HomeNavObj.MapsScreen.route)
                } else {
                    Toast.makeText(homeNavController.context, "Maps", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(imageVector = Icons.Default.Map, contentDescription = HomeNavObj.MapsScreen.route, tint = isHighLighted(currentPage, 1))
            }
            IconButton(onClick = {
                if(currentPage != 2){
                    navbarViewModel.setPageState(2)
                    homeNavController.navigate(HomeNavObj.BookmarkScreen.route)
                } else {
                    Toast.makeText(homeNavController.context, "Bookmarks", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(imageVector = Icons.Default.Bookmark, contentDescription = HomeNavObj.BookmarkScreen.route, tint = isHighLighted(currentPage, 2))
            }
            IconButton(onClick = {
                if(currentPage != 3){
                    navbarViewModel.setPageState(3)
                    homeNavController.navigate(HomeNavObj.ProfileScreen.route)
                } else {
                    Toast.makeText(homeNavController.context, "Profile", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = HomeNavObj.ProfileScreen.route, tint = isHighLighted(currentPage, 3))
            }
        }
    }
}

fun isHighLighted(currentPage: Int, index: Int): Color {
    return if(currentPage == index){
        Color.Blue
    } else {
        Color.Black
    }
}