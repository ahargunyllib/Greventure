package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.Comment
import com.seven_sheesh.greventure.domain.repository.CommentRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val supabaseClientProvider: Postgrest) : CommentRepository {

    private val TAG = "CommentRepositoryImpl"

    override fun getAllComments(): Flow<Pair<String, List<Comment>>> {
        return flow {
            Log.d(TAG, "getAllComments: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val commentsResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("comments").select().decodeList<Comment>()
                }
                val comments = commentsResponse ?: emptyList()
                Log.d(TAG, "getAllComments: Successfully fetched comments")
                emit(Pair("Successfully fetched comments", comments))
            } catch (e: Exception) {
                Log.e(TAG, "getAllComments: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun getCommentById(commentId: String): Flow<Pair<String, Comment?>> {
        return flow {
            Log.d(TAG, "getCommentById: Loading...")
            emit(Pair("Loading...", null))
            try {
                val commentResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("comments")
                        .select {
                            filter {
                                eq("id", commentId)
                            }
                        }
                        .decodeSingleOrNull<Comment>()
                }
                Log.d(TAG, "getCommentById: Comment found")
                emit(Pair("Comment found", commentResponse))
            } catch (e: Exception) {
                Log.e(TAG, "getCommentById: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun getCommentsByThreadId(threadId: String): Flow<Pair<String, List<Comment>>> {
        return flow {
            Log.d(TAG, "getCommentsByThreadId: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val commentsResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("comments")
                        .select {
                            filter {
                                eq("thread_id", threadId)
                            }
                        }
                        .decodeList<Comment>()
                }
                val comments = commentsResponse ?: emptyList()
                Log.d(TAG, "getCommentsByThreadId: Successfully fetched comments")
                emit(Pair("Successfully fetched comments", comments))
            } catch (e: Exception) {
                Log.e(TAG, "getCommentsByThreadId: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun upsertComment(comment: Comment): Flow<String> {
        return flow {
            Log.d(TAG, "upsertComment: Processing...")
            emit("Processing...")
            try {
                withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("comments").upsert(comment)
                }
                Log.d(TAG, "upsertComment: Comment successfully inserted/updated")
                emit("Comment successfully inserted/updated")
            } catch (e: Exception) {
                Log.e(TAG, "upsertComment: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun deleteComment(commentId: String): Flow<String> {
        return flow {
            Log.d(TAG, "deleteComment: Processing...")
            emit("Processing...")
            try {
                withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("comments")
                        .delete {
                            filter {
                                eq("id", commentId)
                            }
                        }
                }
                Log.d(TAG, "deleteComment: Comment successfully deleted")
                emit("Comment successfully deleted")
            } catch (e: Exception) {
                Log.e(TAG, "deleteComment: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }
}
