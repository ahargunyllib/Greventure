package com.seven_sheesh.greventure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: Flow<String> = _name

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: Flow<String> = _phoneNumber

    private val _password = MutableStateFlow("")
    val password: Flow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: Flow<String> = _confirmPassword

    private val _message = MutableStateFlow("")
    val message: Flow<String> = _message

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun onRegister() {
        if (_name.value.isEmpty() || _email.value.isEmpty() || _phoneNumber.value.isEmpty() || _password.value.isEmpty()) {
            _message.value = "Please fill all fields"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
            _message.value = "Invalid email address"
            return
        }

        if (_password.value.length < 8) {
            _message.value = "Password must be at least 8 characters"
            return
        }

        if (_password.value != _confirmPassword.value) {
            _message.value = "Password does not match"
            return
        }

        viewModelScope.launch {
            authenticationRepository.signUp(
                name = _name.value,
                email = _email.value,
                phoneNumber = _phoneNumber.value,
                password = _password.value,
            ).collect { result ->
                _message.value = result
            }
        }
    }
}