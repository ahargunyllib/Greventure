package com.seven_sheesh.greventure.presentation.ui.widget.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.maps.android.compose.Marker
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.EventBubble
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
        MarkerInfoWindow(
            state = MarkerState(position = LatLng(currentLocation.first, currentLocation.second)),
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
            title = "Your Location"
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(GreventureScheme.Success.color)
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                ) {
                    Text("Your Position", fontWeight = FontWeight.Medium, color = GreventureScheme.White.color, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        bubbles.second.forEach { bubble ->
            MarkerInfoWindow(
                state = MarkerState(position = LatLng(bubble.latitude.toDouble(), bubble.longitude.toDouble())),
                icon = getResizedMarkerIcon(
                    EventBubble(bubble.eventType),
                    newWidth = 80,
                    newHeight = 80
                ),
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
                            .background(if(bubble.eventType != null) EventColor(bubble.eventType) else GreventureScheme.Success.color)
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                    ) {
                        Text(bubble.title, fontWeight = FontWeight.Medium, color = GreventureScheme.White.color, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

fun resizeBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
    val matrix = Matrix()
    matrix.postScale(newWidth.toFloat() / bitmap.width, newHeight.toFloat() / bitmap.height)
    val resizedBitmap = Bitmap.createBitmap(
        bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
    )
    return resizedBitmap
}

@Composable
fun getResizedMarkerIcon(resourceId: Int, newWidth: Int, newHeight: Int): BitmapDescriptor {
    val context = LocalContext.current
    val bitmap = BitmapFactory.decodeResource(context.resources, resourceId)
    val resizedBitmap = resizeBitmap(bitmap, newWidth, newHeight)
    return BitmapDescriptorFactory.fromBitmap(resizedBitmap)
}
