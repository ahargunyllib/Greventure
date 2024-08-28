package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.BubblePhoto
import kotlinx.coroutines.flow.Flow

interface BubblePhotoRepository {
    fun getAllBubblePhotos(): Flow<Pair<String, List<BubblePhoto>>>
    fun getBubblePhotoById(photoId: Int): Flow<Pair<String, BubblePhoto?>>
    fun getBubblePhotosByBubbleId(bubbleId: String): Flow<Pair<String, List<BubblePhoto>>>
    fun upsertBubblePhoto(bubblePhoto: BubblePhoto): Flow<String>
    fun deleteBubblePhoto(photoId: Int): Flow<String>
}