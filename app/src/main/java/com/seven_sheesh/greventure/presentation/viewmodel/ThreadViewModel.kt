package com.seven_sheesh.greventure.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven_sheesh.greventure.data.repository.ThreadRepositoryImpl
import com.seven_sheesh.greventure.data.repository.UserRepositoryImpl
import com.seven_sheesh.greventure.domain.model.Thread
import com.seven_sheesh.greventure.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThreadViewModel @Inject constructor(
    private val threadRepository: ThreadRepositoryImpl,
    private val userRepository: UserRepositoryImpl
) : ViewModel() {

    private val _threadListState = MutableStateFlow<Pair<String, List<Thread>>>(Pair("Loading...", emptyList()))
    val threadListState: StateFlow<Pair<String, List<Thread>>> get() = _threadListState

    private val _threadState = MutableStateFlow<Pair<String, Thread?>>(Pair("Loading...", null))
    val threadState: StateFlow<Pair<String, Thread?>> get() = _threadState

    private val _messageState = MutableStateFlow<String>("")
    val messageState: StateFlow<String> get() = _messageState

    private val _threadUserState = MutableStateFlow<Map<Thread, User>?>(null)
    val threadUserState: StateFlow<Map<Thread, User>?> get() = _threadUserState

    private val _threadUserListState = MutableStateFlow<List<Map<Thread, User>>>(emptyList())
    val threadUserListState: StateFlow<List<Map<Thread, User>>> get() = _threadUserListState

    fun loadAllThreads() {
        viewModelScope.launch {
            threadRepository.getAllThreads()
                .collect { threadResult ->
                    _threadListState.value = threadResult

                    val threads = threadResult.second
                    val threadUserList = mutableListOf<Map<Thread, User>>()

                    threads.forEach { thread ->
                        val userId = thread.userId // assuming Thread has a userId field
                        userRepository.getUserById(thread.userId.toString()).collect { userResult ->
                            userResult.second?.let { user ->
                                // Create a map of Thread to User and add it to the list
                                threadUserList.add(mapOf(thread to user))
                            }
                        }
                    }

                    // Update state with the list of maps of threads and their users
                    _threadUserListState.value = threadUserList
                }
        }
    }

    fun loadThreadById(threadId: String) {
        viewModelScope.launch {
            threadRepository.getThreadById(threadId)
                .collect { threadResult ->
                    _threadState.value = threadResult

                    val thread = threadResult.second
                    if (thread != null) {
                        val userId = thread.userId // assuming Thread has a userId field
                        userRepository.getUserById(thread.userId.toString()).collect { userResult ->
                            userResult.second?.let { user ->
                                // Store the thread and user in a single map
                                _threadUserState.value = mapOf(thread to user)
                            }
                        }
                    }
                }
        }
    }

    fun loadThreadByBubbleId(bubbleId: String) {
        viewModelScope.launch {
            threadRepository.getThreadsByBubbleId(bubbleId)
                .collect { threadResult ->
                    _threadListState.value = threadResult

                    val threads = threadResult.second
                    val threadUserList = mutableListOf<Map<Thread, User>>()

                    threads.forEach { thread ->
                        val userId = thread.userId // assuming Thread has a userId field
                        userRepository.getUserById(thread.userId.toString()).collect { userResult ->
                            userResult.second?.let { user ->
                                // Create a map of Thread to User and add it to the list
                                threadUserList.add(mapOf(thread to user))
                            }
                        }
                    }

                    // Update state with the list of maps of threads and their users
                    _threadUserListState.value = threadUserList
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
