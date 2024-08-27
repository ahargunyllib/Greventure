package com.seven_sheesh.greventure.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.seven_sheesh.greventure.data.repository.BubbleRepositoryImpl
import com.seven_sheesh.greventure.domain.model.Bubble
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BubbleListViewModel @Inject constructor(private val bubbleRepository: BubbleRepositoryImpl) : ViewModel() {

    private val _bubbleListState = MutableStateFlow<Pair<String, List<Bubble>>>(Pair("Loading...", emptyList()))
    val bubbleListState: StateFlow<Pair<String, List<Bubble>>> get() = _bubbleListState

    private val _bubbleState = MutableStateFlow<Pair<String, Bubble?>>(Pair("Loading...", null))
    val bubbleState: StateFlow<Pair<String, Bubble?>> get() = _bubbleState

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
}
