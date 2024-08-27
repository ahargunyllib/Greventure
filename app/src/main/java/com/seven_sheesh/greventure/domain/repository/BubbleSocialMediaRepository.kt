package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.BubbleSocialMedia
import kotlinx.coroutines.flow.Flow

interface BubbleSocialMediaRepository {
    fun getAllBubbleSocialMedia(): Flow<Pair<String, List<BubbleSocialMedia>>>
    fun getBubbleSocialMediaById(socialMediaId: Int): Flow<Pair<String, BubbleSocialMedia?>>
    fun getBubbleSocialMediaByBubbleId(bubbleId: String): Flow<Pair<String, List<BubbleSocialMedia>>>
    fun upsertBubbleSocialMedia(bubbleSocialMedia: BubbleSocialMedia): Flow<String>
    fun deleteBubbleSocialMedia(socialMediaId: Int): Flow<String>
}
