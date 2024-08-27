package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.Thread
import com.seven_sheesh.greventure.domain.repository.ThreadRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThreadRepositoryImpl @Inject constructor(private val supabaseClientProvider: Postgrest) : ThreadRepository {

    private val TAG = "ThreadRepositoryImpl"

    override fun getAllThreads(): Flow<Pair<String, List<Thread>>> {
        return flow {
            Log.d(TAG, "getAllThreads: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val threadsResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("threads").select().decodeList<Thread>()
                }
                val threads = threadsResponse ?: emptyList()
                Log.d(TAG, "getAllThreads: Successfully fetched threads")
                emit(Pair("Successfully fetched threads", threads))
            } catch (e: Exception) {
                Log.e(TAG, "getAllThreads: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun getThreadById(threadId: String): Flow<Pair<String, Thread?>> {
        return flow {
            Log.d(TAG, "getThreadById: Loading...")
            emit(Pair("Loading...", null))
            try {
                val threadResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("threads")
                        .select {
                            filter {
                                eq("id", threadId)
                            }
                        }
                        .decodeSingleOrNull<Thread>()
                }
                Log.d(TAG, "getThreadById: Thread found")
                emit(Pair("Thread found", threadResponse))
            } catch (e: Exception) {
                Log.e(TAG, "getThreadById: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun getThreadsByBubbleId(bubbleId: String): Flow<Pair<String, List<Thread>>> {
        return flow {
            Log.d(TAG, "getThreadsByBubbleId: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val threadsResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("threads")
                        .select {
                            filter {
                                eq("bubble_id", bubbleId)
                            }
                        }
                        .decodeList<Thread>()
                }
                val threads = threadsResponse ?: emptyList()
                Log.d(TAG, "getThreadsByBubbleId: Successfully fetched threads")
                emit(Pair("Successfully fetched threads", threads))
            } catch (e: Exception) {
                Log.e(TAG, "getThreadsByBubbleId: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun upsertThread(thread: Thread): Flow<String> {
        return flow {
            Log.d(TAG, "upsertThread: Processing...")
            emit("Processing...")
            try {
                val response = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("threads").upsert(thread)
                }
                Log.d(TAG, "upsertThread: Thread successfully inserted/updated")
                emit("Thread successfully inserted/updated")
            } catch (e: Exception) {
                Log.e(TAG, "upsertThread: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun deleteThread(threadId: String): Flow<String> {
        return flow {
            Log.d(TAG, "deleteThread: Processing...")
            emit("Processing...")
            try {
                val response = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("threads")
                        .delete {
                            filter {
                                eq("id", threadId)
                            }
                        }
                }
                Log.d(TAG, "deleteThread: Thread successfully deleted")
                emit("Thread successfully deleted")
            } catch (e: Exception) {
                Log.e(TAG, "deleteThread: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }
}
