package com.seven_sheesh.greventure.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.seven_sheesh.greventure.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _requestPositionMessage = MutableStateFlow("")
    private val _currentPosition = MutableStateFlow(Pair(-7.9796, 112.6304))
    private val _cameraPositionState = MutableStateFlow(
        CameraPositionState(
            position = CameraPosition.fromLatLngZoom(
                LatLng(_currentPosition.value.first, _currentPosition.value.second), 13f
            )
        )
    )
    private val _uiSettings = MutableStateFlow(
        MapUiSettings(zoomControlsEnabled = true)
    )
    private val _properties = MutableStateFlow(
        MapProperties(
            mapType = MapType.NORMAL,
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.maps)
        )
    )

    val requestPositionMessage: StateFlow<String> get() = _requestPositionMessage
    val currentPosition: StateFlow<Pair<Double, Double>> get() = _currentPosition
    val cameraPositionState: StateFlow<CameraPositionState> get() = _cameraPositionState
    val uiSettings: StateFlow<MapUiSettings> get() = _uiSettings
    val properties: StateFlow<MapProperties> get() = _properties

    fun setRequestPositionMessage(message: String){
        viewModelScope.launch {
            _requestPositionMessage.value = message
        }
    }

    fun updatePosition(lat: Double, lng: Double) {
        viewModelScope.launch {
            _currentPosition.value = Pair(lat, lng)
            _cameraPositionState.value = CameraPositionState(
                position = CameraPosition.fromLatLngZoom(
                    LatLng(lat, lng), 13f
                )
            )
        }
    }

    fun setMapType(mapType: MapType) {
        viewModelScope.launch {
            _properties.value = _properties.value.copy(mapType = mapType)
        }
    }

    fun setUiSettings(zoomControlsEnabled: Boolean) {
        viewModelScope.launch {
            _uiSettings.value = _uiSettings.value.copy(zoomControlsEnabled = zoomControlsEnabled)
        }
    }

    fun setMapStyle(mapStyleOptions: MapStyleOptions?) {
        viewModelScope.launch {
            _properties.value = _properties.value.copy(mapStyleOptions = mapStyleOptions)
        }
    }
}