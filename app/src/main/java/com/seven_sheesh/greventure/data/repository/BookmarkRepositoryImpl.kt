package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.Bookmark
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.BubblePhoto
import com.seven_sheesh.greventure.domain.model.Review
import com.seven_sheesh.greventure.domain.repository.BookmarkRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val db: Postgrest
) : BookmarkRepository {
    private val FILE_NAME = "BookmarkRepositoryImpl"
    override fun getBookmarks(userId: String): Flow<Pair<String, List<Bubble>>> {
        val FUNCTION_NAME = "getBookmark"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit(Pair("Loading", emptyList()))
            try {
                val bookmarks = db.from("bookmarks").select {
                    filter {
                        eq("user_id", userId)
                        eq("is_started", false)
                    }
                }.decodeList<Bookmark>()

                val bubbles = db.from("bubbles").select().decodeList<Bubble>()

                val response = mutableListOf<Bubble>()

                bookmarks.forEach { bookmark ->
                    bubbles.forEach { bubble ->
                        if (bookmark.bubbleId == bubble.id) {
                            val bubblePhoto = db.from("bubble_photos").select {
                                filter {
                                    eq("bubble_id", bubble.id)
                                }
                            }.decodeSingleOrNull<BubblePhoto>()

                            val bubbleReview = db.from("reviews").select {
                                filter {
                                    eq("bubble_id", bubble.id)
                                }
                            }.decodeList<Review>()

                            val averageStar = bubbleReview.map { it.star }.average()

                            val bubbleResponse = bubble.copy(
                                photoUrl = bubblePhoto?.url,
                                averageRating = if (averageStar.isNaN()) 0.0 else averageStar
                            )

                            response.add(bubbleResponse)
                        }
                    }
                }

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Successfully fetched bookmarks")
                emit(Pair("Successfully fetched bookmarks", response))
            } catch (e: Exception) {
                Log.e(FILE_NAME, "[$FUNCTION_NAME] An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun getHistoryBookmarks(userId: String): Flow<Pair<String, List<Bubble>>> {
        val FUNCTION_NAME = "getBookmark"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit(Pair("Loading", emptyList()))
            try {
                val bookmarks = db.from("bookmarks").select {
                    filter {
                        eq("user_id", userId)
                        eq("is_started", true)
                    }
                }.decodeList<Bookmark>()

                val bubbles = db.from("bubbles").select().decodeList<Bubble>()

                val response = mutableListOf<Bubble>()

                bookmarks.forEach { bookmark ->
                    bubbles.forEach { bubble ->
                        if (bookmark.bubbleId == bubble.id) {
                            val bubblePhoto = db.from("bubble_photos").select {
                                filter {
                                    eq("bubble_id", bubble.id)
                                }
                            }.decodeSingleOrNull<BubblePhoto>()

                            val bubbleReview = db.from("reviews").select {
                                filter {
                                    eq("bubble_id", bubble.id)
                                }
                            }.decodeList<Review>()

                            val averageStar = bubbleReview.map { it.star }.average()

                            val bubbleResponse = bubble.copy(
                                photoUrl = bubblePhoto?.url,
                                averageRating = if (averageStar.isNaN()) 0.0 else averageStar
                            )

                            response.add(bubbleResponse)
                        }
                    }
                }

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Successfully fetched bookmarks")
                emit(Pair("Successfully fetched bookmarks", response))
            } catch (e: Exception) {
                Log.e(FILE_NAME, "[$FUNCTION_NAME] An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun getBookmark(userId: String, bubbleId: String): Flow<Pair<String, Bubble?>> {
        val FUNCTION_NAME = "getBookmark"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit(Pair("Loading", null))
            try {
                val bookmark = db.from("bookmarks").select {
                    filter {
                        eq("user_id", userId)
                        eq("bubble_id", bubbleId)
                        eq("is_started", false)
                    }
                }.decodeSingleOrNull<Bookmark>()

                if (bookmark == null) {
                    Log.d(FILE_NAME, "[$FUNCTION_NAME] Bookmark not found")
                    emit(Pair("Bookmark not found", null))
                    return@flow
                }

                val bubble = db.from("bubbles").select {
                    filter {
                        eq("id", bookmark.bubbleId!!)
                    }
                }.decodeSingle<Bubble>()

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Successfully fetched bookmark")
                emit(Pair("Successfully fetched bookmark", bubble))
            } catch (e: Exception) {
                Log.e(FILE_NAME, "[$FUNCTION_NAME] An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun addBookmark(userId: String, bubbleId: String): Flow<String> {
        val FUNCTION_NAME = "addBookmark"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit("Loading")
            try {
                val bubble = db.from("bubbles").select {
                    filter {
                        eq("id", bubbleId)
                    }
                }.decodeSingleOrNull<Bubble>()

                Log.i(FILE_NAME, "addBookmark: $bubbleId")

                if (bubble == null) {
                    Log.d(FILE_NAME, "[$FUNCTION_NAME] Bubble not found")
                    emit("Bubble not found")
                    return@flow
                }

                db.from("bookmarks").upsert(
                    Bookmark(
                        id = UUID.randomUUID().toString(),
                        userId = userId,
                        bubbleId = bubbleId
                    )
                )

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Successfully added bookmark")
                emit("Successfully added bookmark")
            } catch (e: Exception) {
                Log.e(FILE_NAME, "[$FUNCTION_NAME] An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun removeBookmark(userId: String, bubbleId: String): Flow<String> {
        val FUNCTION_NAME = "removeBookmark"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit("Loading")
            try {
                db.from("bookmarks").delete {
                    filter {
                        eq("user_id", userId)
                        eq("bubble_id", bubbleId)
                    }
                }

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Successfully removed bookmark")
                emit("Successfully removed bookmark")
            } catch (e: Exception) {
                Log.e(FILE_NAME, "[$FUNCTION_NAME] An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun getBookmarkPhoto(bubbleId: String): Flow<Pair<String, String?>> {
        val FUNCTION_NAME = "getBookmarkPhoto"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit(Pair("Loading", null))
            try {
                val bookmarkPhoto = db.from("bubble_photos").select {
                    filter {
                        eq("bubble_id", bubbleId)
                    }
                }.decodeSingleOrNull<String>()

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Successfully fetched bookmark photo")
                emit(Pair("Successfully fetched bookmark photo", bookmarkPhoto))
            } catch (e: Exception) {
                Log.e(FILE_NAME, "[$FUNCTION_NAME] An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun getBookmarkAverageReview(bubbleId: String): Flow<Pair<String, Double>> {
        val FUNCTION_NAME = "getBookmarkReviews"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit(Pair("Loading", 0.0))
            try {
                val bookmarkReviews = db.from("bubble_reviews").select {
                    filter {
                        eq("bubble_id", bubbleId)
                    }

                }.decodeList<Review>()

                val averageStar = bookmarkReviews.map { it.star }.average()

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Successfully fetched bookmark reviews")
                emit(Pair("Successfully fetched bookmark reviews", averageStar))
            } catch (e: Exception) {
                Log.e(FILE_NAME, "[$FUNCTION_NAME] An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", 0.0))
            }
        }
    }
}