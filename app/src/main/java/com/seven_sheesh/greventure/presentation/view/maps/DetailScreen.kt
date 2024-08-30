package com.seven_sheesh.greventure.presentation.view.maps

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.widget.maps.CameraCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.DiscussionSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.FAQSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.HeaderSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.LocationCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.PictureCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.TopBar
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun DetailScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel(),
    bubbleId: String = ""
) {
    navbarViewModel.setPageState(1)
    val bubbleViewModel = hiltViewModel<BubbleViewModel>()
    bubbleViewModel.loadBubbleById(bubbleId)
    bubbleViewModel.loadBubblePhotoByBubbleId(bubbleId)
    bubbleViewModel.loadBubbleSocialMediaByBubbleId(bubbleId)

    val user = navbarViewModel.user.collectAsState().value.second

    val currentBubble = bubbleViewModel.bubbleState.collectAsState()
    val currentBubblePhoto = bubbleViewModel.bubblePhotoListState.collectAsState()
    val currentBubbleSocialMedia = bubbleViewModel.bubbleSocialMediaListState.collectAsState()

    Scaffold(
        containerColor = GreventureScheme.White.color,
        contentColor = GreventureScheme.White.color,
        topBar = {
            TopBar(homeNavController, bubbleId, user?.id ?: "")
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .safeDrawingPadding(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item { PictureCard(currentBubblePhoto) }
                item { HeaderSection(currentBubble) }
                item { LocationCard(homeNavController, currentBubble) }
                item { CameraCard(homeNavController, currentBubbleSocialMedia) }
                item { FAQSection() }
                item { DiscussionSection() }
                item { Spacer(modifier = Modifier.height(140.dp)) }
            }
        }
    }
}






