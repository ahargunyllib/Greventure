package com.seven_sheesh.greventure.presentation.view.maps

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.widget.GoogleMapsComponent
import com.seven_sheesh.greventure.presentation.viewmodel.MapsViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.utils.RequestLocationPermission
import com.seven_sheesh.greventure.utils.getCurrentLocation
import com.seven_sheesh.greventure.utils.initializeLocationProvider

@Composable
@Preview
fun MapsScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel()
){
    navbarViewModel.setPageState(1)
    val mapsViewModel = hiltViewModel<MapsViewModel>()
    val context = LocalContext.current

    RequestLocationPermission(
        onPermissionGranted = {
            Log.d("Nearby", "Permission granted")
            initializeLocationProvider(context)
            getCurrentLocation(
                onGetCurrentLocationSuccess = { location ->
                    mapsViewModel.updatePosition(location.first, location.second)
                    Log.d("Nearby", "Location: $location")
                },
                onGetCurrentLocationFailed = { exception ->
                    Log.e("Nearby", "getLastLocation:exception", exception)
                },
                context = context
            )
        },
        onPermissionDenied = {
            Log.d("Nearby", "Permission denied")
        },
        onPermissionsRevoked = {
            Log.d("Nearby", "Permission revoked")
        }
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        GoogleMapsComponent(
            cameraPositionState = mapsViewModel.cameraPositionState.collectAsState().value,
            properties = mapsViewModel.properties.collectAsState().value,
            uiSettings = mapsViewModel.uiSettings.collectAsState().value,
            currentLocation = mapsViewModel.currentPosition.collectAsState().value,
            context = LocalContext.current,
            viewModel = mapsViewModel
        )
    }
}