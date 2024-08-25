package com.seven_sheesh.greventure.presentation.ui.widget.common

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun GoogleMapsComponent(
    cameraPositionState: CameraPositionState,
    properties: MapProperties,
    uiSettings: MapUiSettings,
    currentLocation: Pair<Double, Double>,
    context: Context,
    viewModel: MapsViewModel = hiltViewModel()
) {
    val count = remember { mutableIntStateOf(0) }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings,
    ) {
        // Bekas Compfest
//        MarkerInfoWindow(
//            state = MarkerState(
//                position = LatLng(
//                    currentLocation.first,
//                    currentLocation.second
//                )
//            ),
//            icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_foreground)
//        ) {
//            Column {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier
//                        .border(
//                            BorderStroke(1.dp, Color.DarkGray),
//                            RoundedCornerShape(24.dp)
//                        )
//                        .clip(RoundedCornerShape(24.dp))
//                        .background(
//                            Brush.horizontalGradient(
//                                listOf(
//                                    Color.Magenta,
//                                    Color.Cyan
//                                )
//                            )
//                        )
//                        .padding(20.dp)
//                ) {
//                    Text("Your Location", fontWeight = FontWeight.Bold, color = Color.White)
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//        }

//        branchList.value.forEach { branch ->
//            Marker(
//                state = MarkerState(
//                    position = LatLng(
//                        branch.branchCoordinates.first,
//                        branch.branchCoordinates.second
//                    )
//                ),
//                title = branch.branchName,
//                snippet = branch.branchAddress,
//                onInfoWindowClick = {
//                    selectedCoordinates.value = branch
//                    isStreetView.value = true
//                },
//                onClick = {
//                    count.value++
//                    changeBranch.value = true
//                    selectedCoordinates.value = branch
//                    changeBranch.value = false
//
//                    if(count.value <= 2){
//                        Toast.makeText(context, "Do you know you can also open a street view?\uD83E\uDD73", Toast.LENGTH_LONG).show()
//                    }
//                    false
//                }
//            )
//        }

//        Polyline(
//            points = listOf(
//                LatLng(currentLocation.value.first, currentLocation.value.second),
//                LatLng(
//                    closestBranch.value.first.branchCoordinates.first,
//                    closestBranch.value.first.branchCoordinates.second
//                )
//            ),
//            clickable = true,
//            color = CompfestAqua,
//            width = 5f
//        )
    }
}