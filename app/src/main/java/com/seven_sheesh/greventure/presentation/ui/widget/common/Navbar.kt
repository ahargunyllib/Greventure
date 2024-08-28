package com.seven_sheesh.greventure.presentation.ui.widget.common

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
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
        containerColor = GreventureScheme.White.color,
        modifier = Modifier
            .height(96.dp)
            .shadow(32.dp)
            .border(BorderStroke(1.dp, GreventureScheme.SoftGray.color)),
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
                    Toast.makeText(homeNavController.context, "Beranda", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.size(64.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = if(isCurrentPage(currentPage, 0)) Icons.Default.Home else Icons.Outlined.Home, contentDescription = HomeNavObj.HomeScreen.route, tint = isHighLighted(currentPage, 0), modifier = Modifier.size(28.dp))
                    Text(text = "Beranda", color = isHighLighted(currentPage, 0), fontSize = 10.sp)
                }
            }
            IconButton(onClick = {
                if(currentPage != 1){
                    navbarViewModel.setPageState(1)
                    homeNavController.navigate(HomeNavObj.MapsScreen.route)
                } else {
                    Toast.makeText(homeNavController.context, "Peta", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.size(64.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = if(isCurrentPage(currentPage, 1)) Icons.Default.Map else Icons.Outlined.Map, contentDescription = HomeNavObj.MapsScreen.route, tint = isHighLighted(currentPage, 1), modifier = Modifier.size(28.dp))
                    Text(text = "Peta", color = isHighLighted(currentPage, 1), fontSize = 10.sp)
                }
            }
            IconButton(onClick = {
                if(currentPage != 2){
                    navbarViewModel.setPageState(2)
                    homeNavController.navigate(HomeNavObj.BookmarkScreen.route)
                } else {
                    Toast.makeText(homeNavController.context, "Bookmark", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.size(64.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = if(isCurrentPage(currentPage, 2)) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder, contentDescription = HomeNavObj.BookmarkScreen.route, tint = isHighLighted(currentPage, 2), modifier = Modifier.size(28.dp))
                    Text(text = "Bookmark", color = isHighLighted(currentPage, 2), fontSize = 10.sp)
                }
            }
            IconButton(onClick = {
                if(currentPage != 3){
                    navbarViewModel.setPageState(3)
                    homeNavController.navigate(HomeNavObj.ProfileScreen.route)
                } else {
                    Toast.makeText(homeNavController.context, "Profil", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.size(64.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = if(isCurrentPage(currentPage, 3)) Icons.Default.Person else Icons.Outlined.Person, contentDescription = HomeNavObj.ProfileScreen.route, tint = isHighLighted(currentPage, 3), modifier = Modifier.size(28.dp))
                    Text(text = "Profil", color = isHighLighted(currentPage, 3), fontSize = 10.sp)
                }
            }
        }
    }
}

private fun isHighLighted(currentPage: Int, index: Int): Color {
    return if(currentPage == index){
        GreventureScheme.Primary.color
    } else {
        GreventureScheme.Gray.color
    }
}

private fun isCurrentPage(currentPage: Int, index: Int): Boolean {
    return currentPage == index
}