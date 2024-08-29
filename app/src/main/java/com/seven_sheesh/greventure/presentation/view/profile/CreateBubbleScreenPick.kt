package com.seven_sheesh.greventure.presentation.view.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.GoogleMapsComponent
import com.seven_sheesh.greventure.presentation.viewmodel.CreateBubbleViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.utils.RequestLocationLooper

@Composable
@Preview
fun CreateBubbleScreenPick(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    createBubbleViewModel: CreateBubbleViewModel = hiltViewModel(),
    mapsViewModel: MapsViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(3)
    val bubble = createBubbleViewModel.bubbleState.collectAsState()
    val context = LocalContext.current
    RequestLocationLooper(context = context, mapsViewModel = mapsViewModel)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .safeDrawingPadding(),
        contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(GreventureScheme.Primary.color),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Arrow Back", modifier = Modifier.clickable {
                            homeNavController.navigate(HomeNavObj.CreateBubble2.route)
                        }, tint = GreventureScheme.White.color)
                        Column {
                            Text(text = "Pendaftaran", fontWeight = FontWeight.SemiBold, fontSize = 22.sp, color = GreventureScheme.White.color)
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
            }

            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp), contentAlignment = Alignment.Center){
                    Image(painter = painterResource(id = R.drawable.create_bubble_2), contentDescription = "step 1", modifier = Modifier.height(72.dp))
                }
            }

            item {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    colors = CardDefaults.cardColors(GreventureScheme.White.color),
                    border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ) {
                        Text(text = "Tap sekali peta untuk memilih lokasi", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = GreventureScheme.Black.color)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Lokasi terpilih: ", fontSize = 12.sp, color = GreventureScheme.Gray.color)
                        Text(text = "${bubble.value.latitude} / ${bubble.value.longitude}", fontSize = 12.sp, color = GreventureScheme.Gray.color)
                        Spacer(modifier = Modifier.height(16.dp))

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp),
                            colors = CardDefaults.cardColors(GreventureScheme.White.color),
                            border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
                        ){
                            GoogleMapsComponent(
                                cameraPositionState = mapsViewModel.cameraPositionState.collectAsState().value,
                                properties = mapsViewModel.properties.collectAsState().value,
                                uiSettings = mapsViewModel.uiSettings.collectAsState().value,
                                currentLocation = mapsViewModel.currentPosition.collectAsState().value,
                                context = context,
                                onMapClick = {
                                    createBubbleViewModel.updateBubble(
                                        bubble.value.copy(
                                            latitude = String.format("%.4f", it.latitude).toDouble(),
                                            longitude = String.format("%.4f", it.longitude).toDouble()
                                        )
                                    )
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp),
                            colors = CardDefaults.cardColors(GreventureScheme.Primary.color),
                            shape = RoundedCornerShape(50.dp)
                        ) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    homeNavController.navigate(HomeNavObj.CreateBubble2.route)
                                }, contentAlignment = Alignment.Center){
                                Text(text = "Lanjut", color = GreventureScheme.White.color, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}