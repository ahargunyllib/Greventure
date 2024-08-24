package com.seven_sheesh.greventure.presentation.view.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@Composable
@Preview
fun HomeScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(0)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Home Screen", color = Color.Black)
    }
}