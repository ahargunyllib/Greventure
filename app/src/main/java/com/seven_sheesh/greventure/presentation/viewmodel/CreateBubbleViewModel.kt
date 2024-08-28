package com.seven_sheesh.greventure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.BubblePhoto
import com.seven_sheesh.greventure.domain.model.BubbleSocialMedia
import com.seven_sheesh.greventure.domain.model.PlaceholderData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateBubbleViewModel @Inject constructor(): ViewModel() {
    private val _bubbleState = MutableStateFlow<Bubble>(PlaceholderData.bubbleEmpty)
    val bubbleState = _bubbleState.asStateFlow()

    private val _bubblePhoto = MutableStateFlow<BubblePhoto>(PlaceholderData.bubblePhotoEmpty)
    val bubblePhoto = _bubblePhoto.asStateFlow()

    private val _bubbleSocialMedia = MutableStateFlow<BubbleSocialMedia>(PlaceholderData.bubbleSocialMediaEmpty)
    val bubbleSocialMedia = _bubbleSocialMedia.asStateFlow()

    fun updateBubble(bubble: Bubble) {
        _bubbleState.value = bubble
    }

    fun updateBubblePhoto(bubblePhoto: BubblePhoto) {
        _bubblePhoto.value = bubblePhoto
    }

    fun updateBubbleSocialMedia(bubbleSocialMedia: BubbleSocialMedia) {
        _bubbleSocialMedia.value = bubbleSocialMedia
    }
}