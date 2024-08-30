package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.Bubble
import kotlinx.coroutines.flow.Flow

interface BubbleRepository {
    fun getAllBubbles(): Flow<Pair<String, List<Bubble>>>
    fun getBubbleById(bubbleId: String): Flow<Pair<String, Bubble?>>
    fun getBubbleByUserId(userId: String): Flow<Pair<String, List<Bubble>>>
    fun upsertBubble(bubble: Bubble): Flow<String>
    fun deleteBubble(bubbleId: String): Flow<String>
    fun searchBubbles(query: String): Flow<Pair<String, List<Bubble>>>
}
