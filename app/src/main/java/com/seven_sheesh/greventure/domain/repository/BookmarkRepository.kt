package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.Bubble
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarks(userId: String): Flow<Pair<String, List<Bubble>>>
    fun getBookmark(userId: String, bubbleId: String): Flow<Pair<String, Bubble?>>
    fun addBookmark(userId: String, bubbleId: String): Flow<String>
    fun removeBookmark(userId: String, bubbleId: String): Flow<String>
    fun getBookmarkPhoto(bubbleId: String): Flow<Pair<String, String?>>
    fun getBookmarkAverageReview(bubbleId: String): Flow<Pair<String, Double>>
}