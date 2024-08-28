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
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun GoogleMapsComponent(
    cameraPositionState: CameraPositionState,
    properties: MapProperties,
    uiSettings: MapUiSettings,
    currentLocation: Pair<Double, Double>,
    context: Context,
    viewModel: MapsViewModel = hiltViewModel(),
    onClickMarker: (Pair<String, String>) -> Unit = {}
) {
    // Buat ngetes doang...
    // Function to generate random coordinates around Universitas Brawijaya
    fun generateRandomCoordinates(center: LatLng, radius: Double): LatLng {
        val random = java.util.Random()
        val latOffset = (random.nextDouble() - 0.5) * radius
        val lngOffset = (random.nextDouble() - 0.5) * radius
        return LatLng(center.latitude + latOffset, center.longitude + lngOffset)
    }

    // Define Universitas Brawijaya's coordinates
    val ubCoordinates = LatLng(-7.9570, 112.6133)

    // Generate random markers
    val randomMarkers = List(5) {
        generateRandomCoordinates(ubCoordinates, 0.01)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings,
    ) {
        randomMarkers.forEach { latLng ->
            MarkerInfoWindow(
                state = MarkerState(position = latLng),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                onClick = {
                    onClickMarker("Random Marker" to "Generated near UB")
                    false
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(GreventureScheme.Secondary.color)
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text("Random Marker", fontWeight = FontWeight.Medium, color = GreventureScheme.White.color)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

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
//                    Text("Your Location", fontWeight = FontWeight.Bold, color = GreventureScheme.White.color)
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