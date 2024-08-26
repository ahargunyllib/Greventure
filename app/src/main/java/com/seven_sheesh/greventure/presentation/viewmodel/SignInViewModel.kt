package com.seven_sheesh.greventure.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UserState {
    data object Loading: UserState()
    data class Success(val message: String): UserState()
    data class Error(val message: String): UserState()
}

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password = _password

    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onSignIn() {
        viewModelScope.launch {
            authenticationRepository.signIn(
                email = _email.value,
                password = _password.value
            )
        }
    }

    fun onGoogleSignIn(googleIdToken: String, rawNonce: String) {
        viewModelScope.launch{
            authenticationRepository.signInWithNativeGoogle(googleIdToken, rawNonce)
        }
    }
}
