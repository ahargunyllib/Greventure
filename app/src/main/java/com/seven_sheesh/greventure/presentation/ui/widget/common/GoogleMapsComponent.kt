package com.seven_sheesh.greventure.presentation.ui.widget.common

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.Marker
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.EventColor
import com.seven_sheesh.greventure.domain.model.EventType
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun GoogleMapsComponent(
    cameraPositionState: CameraPositionState,
    properties: MapProperties,
    uiSettings: MapUiSettings,
    currentLocation: Pair<Double, Double>,
    context: Context,
    viewModel: MapsViewModel = hiltViewModel(),
    onClickMarker: (Bubble) -> Unit = {},
    onMapClick: (LatLng) -> Unit = {}
) {
    val bubbleViewModel = hiltViewModel<BubbleViewModel>()
    val bubbles by bubbleViewModel.bubbleListState.collectAsState()

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings,
        onMapClick = {
            onMapClick(it)
        }
    ) {
        Marker(
            state = MarkerState(position = LatLng(currentLocation.first, currentLocation.second)),
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
            title = "Your Location"
        )

        bubbles.second.forEach { bubble ->
            MarkerInfoWindow(
                state = MarkerState(position = LatLng(bubble.latitude.toDouble(), bubble.longitude.toDouble())),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                onClick = {
                    onClickMarker(bubble)
                    false
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(EventColor(bubble.eventType ?: EventType.Masyarakat))
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Text(bubble.title, fontWeight = FontWeight.Medium, color = GreventureScheme.White.color)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
