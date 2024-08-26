package com.seven_sheesh.greventure.presentation.view.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.widget.common.GoogleMapsComponent
import com.seven_sheesh.greventure.presentation.ui.widget.home.EventList
import com.seven_sheesh.greventure.presentation.ui.widget.home.MapsCard
import com.seven_sheesh.greventure.presentation.ui.widget.home.NewsList
import com.seven_sheesh.greventure.presentation.ui.widget.home.ProfileBar
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.utils.RequestLocationLooper

@Composable
@Preview
fun HomeScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(0)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(GreventureScheme.White.color)
        .safeDrawingPadding(),
        contentAlignment = Alignment.Center){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            item{
                ProfileBar(homeNavController)
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
                    MapsCard(homeNavController)
                    EventList(homeNavController)
                    NewsList(homeNavController)
                }
            }

            item {
                Spacer(modifier = Modifier.height(140.dp))
            }
        }
    }
}