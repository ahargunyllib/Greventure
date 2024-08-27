package com.seven_sheesh.greventure.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.data.repository.BubbleSocialMediaRepositoryImpl
import com.seven_sheesh.greventure.domain.model.BubbleSocialMedia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BubbleSocialMediaViewModel @Inject constructor(
    private val bubbleSocialMediaRepository: BubbleSocialMediaRepositoryImpl
) : ViewModel() {

    private val _bubbleSocialMediaListState = MutableStateFlow<Pair<String, List<BubbleSocialMedia>>>(Pair("Loading...", emptyList()))
    val bubbleSocialMediaListState: StateFlow<Pair<String, List<BubbleSocialMedia>>> get() = _bubbleSocialMediaListState

    private val _bubbleSocialMediaState = MutableStateFlow<Pair<String, BubbleSocialMedia?>>(Pair("Loading...", null))
    val bubbleSocialMediaState: StateFlow<Pair<String, BubbleSocialMedia?>> get() = _bubbleSocialMediaState

    private val _messageState = MutableStateFlow<String>("")
    val messageState: StateFlow<String> get() = _messageState

    fun loadAllBubbleSocialMedias() {
        viewModelScope.launch {
            bubbleSocialMediaRepository.getAllBubbleSocialMedia()
                .collect { result ->
                    _bubbleSocialMediaListState.value = result
                }
        }
    }

    fun loadBubbleSocialMediaById(bubbleSocialMediaId: Int) {
        viewModelScope.launch {
            bubbleSocialMediaRepository.getBubbleSocialMediaById(bubbleSocialMediaId)
                .collect { result ->
                    _bubbleSocialMediaState.value = result
                }
        }
    }

    fun upsertBubbleSocialMedia(bubbleSocialMedia: BubbleSocialMedia) {
        viewModelScope.launch {
            bubbleSocialMediaRepository.upsertBubbleSocialMedia(bubbleSocialMedia)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }

    fun deleteBubbleSocialMedia(bubbleSocialMediaId: Int) {
        viewModelScope.launch {
            bubbleSocialMediaRepository.deleteBubbleSocialMedia(bubbleSocialMediaId)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }
}
