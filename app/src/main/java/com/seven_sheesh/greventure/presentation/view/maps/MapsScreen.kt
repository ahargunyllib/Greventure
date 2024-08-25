package com.seven_sheesh.greventure.presentation.view.maps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.widget.common.GoogleMapsComponent
import com.seven_sheesh.greventure.presentation.ui.widget.common.SearchBarComponent
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.utils.RequestLocationLooper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun MapsScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(1)
    val context = LocalContext.current
    RequestLocationLooper(context = context, mapsViewModel = mapsViewModel)

    Scaffold(
        containerColor = Color.White,
        contentColor = Color.White,
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                SearchBarComponent()
            }
        }
    ){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            GoogleMapsComponent(
                cameraPositionState = mapsViewModel.cameraPositionState.collectAsState().value,
                properties = mapsViewModel.properties.collectAsState().value,
                uiSettings = mapsViewModel.uiSettings.collectAsState().value,
                currentLocation = mapsViewModel.currentPosition.collectAsState().value,
                context = LocalContext.current,
                viewModel = mapsViewModel
            )
        }
    }
}