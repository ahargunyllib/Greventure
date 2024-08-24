package com.seven_sheesh.greventure.presentation.view.maps

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@Composable
@Preview
fun MapsScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(1)
}