package com.seven_sheesh.greventure.presentation.view.maps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.seven_sheesh.greventure.presentation.ui.widget.maps.PopupCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun MapsScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel()
) {
    navbarViewModel.setPageState(1)
    val context = LocalContext.current
    val isMarkerClicked = remember { mutableStateOf(false) }
    RequestLocationLooper(context = context, mapsViewModel = mapsViewModel)

    Scaffold(
        containerColor = Color.White,
        contentColor = Color.White,
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                SearchBarComponent()
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(animationSpec = tween(durationMillis = 300))
                    .background(Color.White)
            ) {
                if (isMarkerClicked.value) {
                    PopupCard(onClick = {
                        isMarkerClicked.value = false
                    })
                    Spacer(modifier = Modifier.height(96.dp))
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GoogleMapsComponent(
                cameraPositionState = mapsViewModel.cameraPositionState.collectAsState().value,
                properties = mapsViewModel.properties.collectAsState().value,
                uiSettings = mapsViewModel.uiSettings.collectAsState().value,
                currentLocation = mapsViewModel.currentPosition.collectAsState().value,
                context = LocalContext.current,
                viewModel = mapsViewModel,
                onClickMarker = {
                    isMarkerClicked.value = true
                }
            )
        }
    }
}
