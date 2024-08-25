package com.seven_sheesh.greventure.presentation.view.maps

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.Accordion
import com.seven_sheesh.greventure.presentation.ui.widget.common.SearchBarComponent
import com.seven_sheesh.greventure.presentation.ui.widget.maps.CameraCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.DiscussionSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.FAQSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.HeaderSection
import com.seven_sheesh.greventure.presentation.ui.widget.maps.LocationCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.PictureCard
import com.seven_sheesh.greventure.presentation.ui.widget.maps.TopBar
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun DetailScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel()
) {
    navbarViewModel.setPageState(1)
    Scaffold(
        containerColor = Color.White,
        contentColor = Color.White,
        topBar = {
            TopBar(homeNavController)
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
                item { PictureCard() }
                item { HeaderSection() }
                item { LocationCard(homeNavController) }
                item { CameraCard(homeNavController) }
                item { FAQSection() }
                item { DiscussionSection() }
                item { Spacer(modifier = Modifier.height(140.dp)) }
            }
        }
    }
}






