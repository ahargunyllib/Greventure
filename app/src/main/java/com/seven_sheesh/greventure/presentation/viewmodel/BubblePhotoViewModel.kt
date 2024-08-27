package com.seven_sheesh.greventure.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.data.repository.BubblePhotoRepositoryImpl
import com.seven_sheesh.greventure.domain.model.BubblePhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BubblePhotoViewModel @Inject constructor(
    private val bubblePhotoRepository: BubblePhotoRepositoryImpl
) : ViewModel() {

    private val _bubblePhotoListState = MutableStateFlow<Pair<String, List<BubblePhoto>>>(Pair("Loading...", emptyList()))
    val bubblePhotoListState: StateFlow<Pair<String, List<BubblePhoto>>> get() = _bubblePhotoListState

    private val _bubblePhotoState = MutableStateFlow<Pair<String, BubblePhoto?>>(Pair("Loading...", null))
    val bubblePhotoState: StateFlow<Pair<String, BubblePhoto?>> get() = _bubblePhotoState

    private val _messageState = MutableStateFlow<String>("")
    val messageState: StateFlow<String> get() = _messageState

    fun loadAllBubblePhotos() {
        viewModelScope.launch {
            bubblePhotoRepository.getAllBubblePhotos()
                .collect { result ->
                    _bubblePhotoListState.value = result
                }
        }
    }

    fun loadBubblePhotoById(bubblePhotoId: Int) {
        viewModelScope.launch {
            bubblePhotoRepository.getBubblePhotoById(bubblePhotoId)
                .collect { result ->
                    _bubblePhotoState.value = result
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
}
