package com.seven_sheesh.greventure.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.data.repository.CommentRepositoryImpl
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.Comment
import com.seven_sheesh.greventure.domain.model.Thread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentRepository: CommentRepositoryImpl
) : ViewModel() {

    private val _commentListState = MutableStateFlow<Pair<String, List<Comment>>>(Pair("Loading...", emptyList()))
    val commentListState: StateFlow<Pair<String, List<Comment>>> get() = _commentListState

    private val _commentState = MutableStateFlow<Pair<String, Comment?>>(Pair("Loading...", null))
    val commentState: StateFlow<Pair<String, Comment?>> get() = _commentState

    private val _messageState = MutableStateFlow<String>("")
    val messageState: StateFlow<String> get() = _messageState

    fun loadAllComments() {
        viewModelScope.launch {
            commentRepository.getAllComments()
                .collect { result ->
                    _commentListState.value = result
                }
        }
    }

    fun loadCommentById(commentId: String) {
        viewModelScope.launch {
            commentRepository.getCommentById(commentId)
                .collect { result ->
                    _commentState.value = result
                }
        }
    }

    fun loadCommentFromThreadList(list: List<Thread>){
        viewModelScope.launch {
            list.forEach{
                commentRepository.getCommentsByThreadId(it.id)
                    .collect { result ->
                        _commentListState.value = _commentListState.value.copy(second = _commentListState.value.second + result.second)
                    }
            }
        }
    }

    fun upsertComment(comment: Comment) {
        viewModelScope.launch {
            commentRepository.upsertComment(comment)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }

    fun deleteComment(commentId: String) {
        viewModelScope.launch {
            commentRepository.deleteComment(commentId)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }
}
