package com.seven_sheesh.greventure.presentation.ui.widget.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.GoogleMapsComponent
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.utils.RequestLocationLooper

@Composable
@Preview
fun MapsCard(
    homeNavController: NavController = rememberNavController(),
    mapsViewModel: MapsViewModel = hiltViewModel()
){
    val context = LocalContext.current
    RequestLocationLooper(context = context, mapsViewModel = mapsViewModel)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Di Sekitarmu", fontSize = 18.sp, color = GreventureScheme.Black.color, fontWeight = FontWeight.SemiBold)
//        Text(text = "Lihat", color = GreventureScheme.Secondary.color, fontSize = 18.sp, modifier = Modifier.clickable {
//            homeNavController.navigate(HomeNavObj.MapsScreen.route)
//        }, textDecoration = TextDecoration.Underline)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(GreventureScheme.White.color),
        border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            GoogleMapsComponent(
                cameraPositionState = mapsViewModel.cameraPositionState.collectAsState().value,
                properties = mapsViewModel.properties.collectAsState().value,
                uiSettings = mapsViewModel.uiSettings.collectAsState().value,
                currentLocation = mapsViewModel.currentPosition.collectAsState().value,
                context = LocalContext.current,
                viewModel = mapsViewModel
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), contentAlignment = Alignment.TopEnd){
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(GreventureScheme.Primary.color), shape = RoundedCornerShape(16.dp)) {
                    Text(text = "Buka Peta")
                }
            }
        }
    }
}