package com.seven_sheesh.greventure.presentation.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.widget.home.EventList
import com.seven_sheesh.greventure.presentation.ui.widget.home.MapsCard
import com.seven_sheesh.greventure.presentation.ui.widget.home.NewsList
import com.seven_sheesh.greventure.presentation.ui.widget.home.ProfileBar
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel

@Composable
@Preview
fun HomeScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(0)
    val bubbleViewModel = hiltViewModel<BubbleViewModel>()

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
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)) {
                    MapsCard(homeNavController, mapsViewModel, bubbleViewModel)
                    EventList(homeNavController, bubbleViewModel)
                    NewsList(homeNavController)
                }
            }

            item {
                Spacer(modifier = Modifier.height(140.dp))
            }
        }
    }
}