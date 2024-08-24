package com.seven_sheesh.greventure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NavbarViewModel @Inject constructor(): ViewModel() {
    /* Page Value
    *  0 -> HomeScreen
    *  1 -> MapsScreen
    *  2 -> BookmarkScreen
    *  3 -> NewsScreen
    * */

    private val _pageState = MutableStateFlow<Int>(0)
    val pageState: StateFlow<Int> get() = _pageState

    fun setPageState(page: Int) {
        _pageState.value = page
    }
}