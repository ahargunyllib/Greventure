package com.seven_sheesh.greventure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.data.repository.AuthenticationRepositoryImpl
import com.seven_sheesh.greventure.domain.model.User
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavbarViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    /* Page Value
    *  0 -> HomeScreen
    *  1 -> MapsScreen
    *  2 -> BookmarkScreen
    *  3 -> ProfileScreen
    * */

    private val _pageState = MutableStateFlow<Int>(0)
    val pageState: StateFlow<Int> get() = _pageState

    private val _user = MutableStateFlow<Pair<String, User?>>(Pair("Loading", null))
    val user: MutableStateFlow<Pair<String, User?>> get() = _user

    init {
        loadUser()
    }

    fun setPageState(page: Int) {
        _pageState.value = page
    }

    private fun loadUser() {
        viewModelScope.launch {
            authenticationRepository.me().collect { result ->
                _user.value = result
            }
        }
    }
}