package com.seven_sheesh.greventure.presentation.ui.widget.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.GoogleMapsComponent
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel
import com.seven_sheesh.greventure.utils.RequestLocationLooper
import com.seven_sheesh.greventure.utils.find3ClosestBubbles

@Composable
@Preview
fun MapsCard(
    homeNavController: NavController = rememberNavController(),
    mapsViewModel: MapsViewModel = hiltViewModel(),
    bubbleViewModel: BubbleViewModel = hiltViewModel()
) {
    val currentPosition = mapsViewModel.currentPosition.collectAsState()
    val context = LocalContext.current
    val bubbleList = bubbleViewModel.bubbleListState.collectAsState()

    val closestBubble by remember(currentPosition, bubbleList) {
        derivedStateOf {
            find3ClosestBubbles(currentPosition.value.first, currentPosition.value.second, bubbleList.value.second)
        }
    }

    RequestLocationLooper(context = context, mapsViewModel = mapsViewModel)

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Di Sekitarmu",
                fontSize = 18.sp,
                color = GreventureScheme.Black.color,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(),
            colors = CardDefaults.cardColors(GreventureScheme.White.color),
            border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                GoogleMapsComponent(
                    cameraPositionState = mapsViewModel.cameraPositionState.collectAsState().value,
                    properties = mapsViewModel.properties.collectAsState().value,
                    uiSettings = mapsViewModel.uiSettings.collectAsState().value,
                    currentLocation = currentPosition.value,
                    context = LocalContext.current,
                    viewModel = mapsViewModel
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Button(
                        onClick = {
                            homeNavController.navigate(HomeNavObj.MapsScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(GreventureScheme.Primary.color),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Buka Peta")
                    }
                }
            }
        }

        if (closestBubble.isNotEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            closestBubble.forEach { bubble ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            homeNavController.navigate(HomeNavObj.DetailScreen.createRoute(bubble.first.id))
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier.size(48.dp),
                            shape = RoundedCornerShape(50),
                            colors = CardDefaults.cardColors(GreventureScheme.White.color),
                            border = BorderStroke(2.dp, GreventureScheme.PrimaryVariant2.color)
                        ) {}
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.fillMaxSize(0.8f)) {
                            Text(
                                text = bubble.first.title,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = GreventureScheme.Black.color
                            )
                            Text(
                                text = bubble.first.description,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 2.dp),
                                color = GreventureScheme.Black.color,
                                maxLines = 1
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${Math.round(bubble.second)} m",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 2.dp),
                            color = GreventureScheme.Black.color
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = "Arrow",
                            tint = GreventureScheme.Black.color
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

