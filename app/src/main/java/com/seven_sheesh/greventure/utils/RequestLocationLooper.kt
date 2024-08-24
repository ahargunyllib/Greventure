package com.seven_sheesh.greventure.utils

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import kotlinx.coroutines.delay

@Composable
fun RequestLocationLooper(
    mapsViewModel: MapsViewModel = hiltViewModel(),
    context: Context
) {
    val shouldRequestPermission = remember { mutableStateOf(true) }
    val message = mapsViewModel.requestPositionMessage.collectAsState().value

    LaunchedEffect(Unit) {
        while (true) {
            shouldRequestPermission.value = true
            delay(20000)
        }
    }

    LaunchedEffect(message) {
        Log.d("Location Looper", message)
    }

    if (shouldRequestPermission.value) {
        RequestLocationPermission(
            onPermissionGranted = {
                mapsViewModel.setRequestPositionMessage("Permission Granted")
                initializeLocationProvider(context)
                getCurrentLocation(
                    onGetCurrentLocationSuccess = { location ->
                        mapsViewModel.updatePosition(location.first, location.second)
                        mapsViewModel.setRequestPositionMessage("Location: $location")
                    },
                    onGetCurrentLocationFailed = { exception ->
                        mapsViewModel.setRequestPositionMessage(exception.message.toString())
                    },
                    context = context
                )
                shouldRequestPermission.value = false
            },
            onPermissionDenied = {
                mapsViewModel.setRequestPositionMessage("Permission Denied")
                shouldRequestPermission.value = false
            },
            onPermissionsRevoked = {
                mapsViewModel.setRequestPositionMessage("Permission Revoked")
                shouldRequestPermission.value = false
            }
        )
    }
}
