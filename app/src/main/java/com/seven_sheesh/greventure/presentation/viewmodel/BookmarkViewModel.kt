package com.seven_sheesh.greventure.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _bookmarks =
        MutableStateFlow<Pair<String, List<Bubble>>>(Pair("Loading", emptyList()))
    val bookmarks: MutableStateFlow<Pair<String, List<Bubble>>> get() = _bookmarks

    private val _bookmark = MutableStateFlow<Pair<String, Bubble?>>(Pair("Loading", null))
    val bookmark: MutableStateFlow<Pair<String, Bubble?>> get() = _bookmark

    private val _bookmarkPhoto = MutableStateFlow<Pair<String, String?>>(Pair("Loading", null))

    private val _message = MutableStateFlow("")
    val message: MutableStateFlow<String> get() = _message

    fun loadBookmarks(userId: String) {
        viewModelScope.launch {
            bookmarkRepository.getBookmarks(userId).collect { result ->
                _bookmarks.value = result
            }
        }
    }

    fun addBookmark(userId: String, bubbleId: String) {
        viewModelScope.launch {
            bookmarkRepository.addBookmark(userId, bubbleId).collect { result ->
                _message.value = result
            }
        }
    }

    fun removeBookmark(userId: String, bubbleId: String) {
        viewModelScope.launch {
            bookmarkRepository.removeBookmark(userId, bubbleId).collect { result ->
                _message.value = result
            }
        }
    }

    fun getBookmark(userId: String, bubbleId: String) {
        viewModelScope.launch {
            bookmarkRepository.getBookmark(userId, bubbleId).collect { result ->
                _bookmark.value = result
            }
        }
    }
}