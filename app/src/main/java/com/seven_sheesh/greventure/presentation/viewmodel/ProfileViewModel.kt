package com.seven_sheesh.greventure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.domain.model.User
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _user = MutableStateFlow<Pair<String, User?>>(Pair("Loading", null))
    val user: MutableStateFlow<Pair<String, User?>> get() = _user

    private val _message = MutableStateFlow("")
    val message: MutableStateFlow<String> get() = _message

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            authenticationRepository.me().collect { result ->
                _user.value = result
            }
        }
    }

    fun onSignOut() {
        viewModelScope.launch {
            authenticationRepository.signOut().collect { result ->
                _message.value = result
            }
        }
    }
}