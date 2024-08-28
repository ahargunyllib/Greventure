package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.Thread
import kotlinx.coroutines.flow.Flow

interface ThreadRepository {
    fun getAllThreads(): Flow<Pair<String, List<Thread>>>
    fun getThreadById(threadId: String): Flow<Pair<String, Thread?>>
    fun getThreadsByBubbleId(bubbleId: String): Flow<Pair<String, List<Thread>>>
    fun upsertThread(thread: Thread): Flow<String>
    fun deleteThread(threadId: String): Flow<String>
}
