package com.seven_sheesh.greventure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password: Flow<String> = _password

    private val _message = MutableStateFlow("")
    val message: Flow<String> = _message

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onSignIn() {
        if (_email.value.isEmpty() || _password.value.isEmpty()) {
            _message.value = "Please fill all fields"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()){
            _message.value = "Please enter a valid email"
            return
        }

        if (_password.value.length < 8) {
            _message.value = "Password must be at least 8 characters"
            return
        }


        viewModelScope.launch {
            authenticationRepository.signIn(_email.value, _password.value)
                .collect { result ->
                    _message.value = result
                }
        }
    }

    fun onGoogleSignIn(googleIdToken: String, rawNonce: String) {
        viewModelScope.launch {
            authenticationRepository.signInWithNativeGoogle(googleIdToken, rawNonce)
                .collect {result ->
                    _message.value = result
                }
        }
    }
}