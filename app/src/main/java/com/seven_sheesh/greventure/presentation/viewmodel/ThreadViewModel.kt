package com.seven_sheesh.greventure.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.data.repository.ThreadRepositoryImpl
import com.seven_sheesh.greventure.domain.model.Thread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThreadViewModel @Inject constructor(
    private val threadRepository: ThreadRepositoryImpl
) : ViewModel() {

    private val _threadListState = MutableStateFlow<Pair<String, List<Thread>>>(Pair("Loading...", emptyList()))
    val threadListState: StateFlow<Pair<String, List<Thread>>> get() = _threadListState

    private val _threadState = MutableStateFlow<Pair<String, Thread?>>(Pair("Loading...", null))
    val threadState: StateFlow<Pair<String, Thread?>> get() = _threadState

    private val _messageState = MutableStateFlow<String>("")
    val messageState: StateFlow<String> get() = _messageState

    fun loadAllThreads() {
        viewModelScope.launch {
            threadRepository.getAllThreads()
                .collect { result ->
                    _threadListState.value = result
                }
        }
    }

    fun loadThreadById(threadId: String) {
        viewModelScope.launch {
            threadRepository.getThreadById(threadId)
                .collect { result ->
                    _threadState.value = result
                }
        }
    }

    fun upsertThread(thread: Thread) {
        viewModelScope.launch {
            threadRepository.upsertThread(thread)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }

    fun deleteThread(threadId: String) {
        viewModelScope.launch {
            threadRepository.deleteThread(threadId)
                .collect { message ->
                    _messageState.value = message
                }
        }
    }
}
