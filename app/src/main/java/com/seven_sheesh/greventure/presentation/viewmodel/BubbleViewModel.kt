package com.seven_sheesh.greventure.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.data.repository.BubblePhotoRepositoryImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.seven_sheesh.greventure.data.repository.BubbleRepositoryImpl
import com.seven_sheesh.greventure.data.repository.BubbleSocialMediaRepositoryImpl
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.BubblePhoto
import com.seven_sheesh.greventure.domain.model.BubbleSocialMedia
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BubbleViewModel @Inject constructor(
    private val bubbleRepository: BubbleRepositoryImpl,
    private val bubblePhotoRepository: BubblePhotoRepositoryImpl,
    private val bubbleSocialMediaRepository: BubbleSocialMediaRepositoryImpl
) : ViewModel() {

    private val _bubbleListState = MutableStateFlow<Pair<String, List<Bubble>>>(Pair("Loading...", emptyList()))
    val bubbleListState: StateFlow<Pair<String, List<Bubble>>> get() = _bubbleListState

    private val _bubbleState = MutableStateFlow<Pair<String, Bubble?>>(Pair("Loading...", null))
    val bubbleState: StateFlow<Pair<String, Bubble?>> get() = _bubbleState

    private val _bubblePhotoListState = MutableStateFlow<Pair<String, List<BubblePhoto>>>(Pair("Loading...", emptyList()))
    val bubblePhotoListState: StateFlow<Pair<String, List<BubblePhoto>>> get() = _bubblePhotoListState

    private val _bubblePhotoState = MutableStateFlow<Pair<String, BubblePhoto?>>(Pair("Loading...", null))
    val bubblePhotoState: StateFlow<Pair<String, BubblePhoto?>> get() = _bubblePhotoState

    private val _bubbleSocialMediaListState = MutableStateFlow<Pair<String, List<BubbleSocialMedia>>>(Pair("Loading...", emptyList()))
    val bubbleSocialMediaListState: StateFlow<Pair<String, List<BubbleSocialMedia>>> get() = _bubbleSocialMediaListState

    private val _bubbleSocialMediaState = MutableStateFlow<Pair<String, BubbleSocialMedia?>>(Pair("Loading...", null))
    val bubbleSocialMediaState: StateFlow<Pair<String, BubbleSocialMedia?>> get() = _bubbleSocialMediaState

    private val _messageState = MutableStateFlow<String>("")
    val messageState: StateFlow<String> get() = _messageState

    init {
        loadAllBubbles()
    }

    fun loadAllBubbles() {
        viewModelScope.launch {
            bubbleRepository.getAllBubbles()
                .collect { result ->
                    _bubbleListState.value = result
                }
        }
    }

    fun loadBubbleById(bubbleId: String) {
        viewModelScope.launch {
            bubbleRepository.getBubbleById(bubbleId)
                .collect { result ->
                    _bubbleState.value = result
                }
        }
    }

    fun upsertBubble(bubble: Bubble) {
        viewModelScope.launch {
            bubbleRepository.upsertBubble(bubble)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }

    fun deleteBubble(bubbleId: String) {
        viewModelScope.launch {
            bubbleRepository.deleteBubble(bubbleId)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }

    fun searchBubbles(query: String) {
        viewModelScope.launch {
            bubbleRepository.searchBubbles(query)
                .collect { result ->
                    _bubbleListState.value = result
                }
        }
    }

    fun loadAllBubblePhotos() {
        viewModelScope.launch {
            bubblePhotoRepository.getAllBubblePhotos()
                .collect { result ->
                    _bubblePhotoListState.value = result
                }
        }
    }

    fun loadBubblePhotoByBubbleId(bubbleId: String) {
        viewModelScope.launch {
            bubblePhotoRepository.getBubblePhotosByBubbleId(bubbleId)
                .collect { result ->
                    _bubblePhotoListState.value = result
                }
        }
    }

    fun upsertBubblePhoto(bubblePhoto: BubblePhoto) {
        viewModelScope.launch {
            bubblePhotoRepository.upsertBubblePhoto(bubblePhoto)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }

    fun deleteBubblePhoto(bubblePhotoId: Int) {
        viewModelScope.launch {
            bubblePhotoRepository.deleteBubblePhoto(bubblePhotoId)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }

    fun loadAllBubbleSocialMedias() {
        viewModelScope.launch {
            bubbleSocialMediaRepository.getAllBubbleSocialMedia()
                .collect { result ->
                    _bubbleSocialMediaListState.value = result
                }
        }
    }

    fun loadBubbleSocialMediaByBubbleId(bubbleId: String) {
        viewModelScope.launch {
            bubbleSocialMediaRepository.getBubbleSocialMediaByBubbleId(bubbleId)
                .collect { result ->
                    _bubbleSocialMediaListState.value = result
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
