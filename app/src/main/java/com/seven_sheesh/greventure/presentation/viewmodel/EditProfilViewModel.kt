package com.seven_sheesh.greventure.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfilViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _photoUri = MutableStateFlow(Uri.EMPTY)
    val photoUri: Flow<Uri> = _photoUri

    private val _name = MutableStateFlow("")
    val name: Flow<String> = _name

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: Flow<String> = _phoneNumber

    private val _message = MutableStateFlow("")
    val message: Flow<String> = _message

    fun onPhotoUriChange(photoUri: Uri?) {
        _photoUri.value = photoUri
    }

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun onEditProfile() {
        viewModelScope.launch {
            if (_name.value.isEmpty() || _email.value.isEmpty() || _phoneNumber.value.isEmpty()) {
                _message.value = "Please fill all fields"
                return@launch
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
                _message.value = "Invalid email"
                return@launch
            }

            authenticationRepository.editProfile(
                _name.value,
                _email.value,
                _phoneNumber.value,
                _photoUri.value
            )
                .collect {
                    _message.value = it
                }
        }
    }
}