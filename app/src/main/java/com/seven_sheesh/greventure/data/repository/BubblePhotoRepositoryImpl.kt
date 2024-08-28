package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.BubblePhoto
import com.seven_sheesh.greventure.domain.repository.BubblePhotoRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BubblePhotoRepositoryImpl @Inject constructor(private val supabaseClientProvider: Postgrest) : BubblePhotoRepository {

    private val TAG = "BubblePhotoRepositoryImpl"

    override fun getAllBubblePhotos(): Flow<Pair<String, List<BubblePhoto>>> {
        return flow {
            Log.d(TAG, "getAllBubblePhotos: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val bubblePhotosResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_photos").select().decodeList<BubblePhoto>()
                }
                val bubblePhotos = bubblePhotosResponse ?: emptyList()
                Log.d(TAG, "getAllBubblePhotos: Successfully fetched bubble photos")
                emit(Pair("Successfully fetched bubble photos", bubblePhotos))
            } catch (e: Exception) {
                Log.e(TAG, "getAllBubblePhotos: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun getBubblePhotoById(photoId: Int): Flow<Pair<String, BubblePhoto?>> {
        return flow {
            Log.d(TAG, "getBubblePhotoById: Loading...")
            emit(Pair("Loading...", null))
            try {
                val bubblePhotoResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_photos")
                        .select {
                            filter {
                                eq("id", photoId)
                            }
                        }
                        .decodeSingleOrNull<BubblePhoto>()
                }
                Log.d(TAG, "getBubblePhotoById: Bubble photo found")
                emit(Pair("Bubble photo found", bubblePhotoResponse))
            } catch (e: Exception) {
                Log.e(TAG, "getBubblePhotoById: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun getBubblePhotosByBubbleId(bubbleId: String): Flow<Pair<String, List<BubblePhoto>>> {
        return flow {
            Log.d(TAG, "getBubblePhotosByBubbleId: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val bubblePhotosResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_photos")
                        .select {
                            filter {
                                eq("bubble_id", bubbleId)
                            }
                        }
                        .decodeList<BubblePhoto>()
                }
                val bubblePhotos = bubblePhotosResponse ?: emptyList()
                Log.d(TAG, "getBubblePhotosByBubbleId: Successfully fetched bubble photos")
                emit(Pair("Successfully fetched bubble photos", bubblePhotos))
            } catch (e: Exception) {
                Log.e(TAG, "getBubblePhotosByBubbleId: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun upsertBubblePhoto(bubblePhoto: BubblePhoto): Flow<String> {
        return flow {
            Log.d(TAG, "upsertBubblePhoto: Processing...")
            emit("Processing...")
            try {
                val response = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_photos").upsert(bubblePhoto)
                }
                Log.d(TAG, "upsertBubblePhoto: Bubble photo successfully inserted/updated")
                emit("Bubble photo successfully inserted/updated")
            } catch (e: Exception) {
                Log.e(TAG, "upsertBubblePhoto: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun deleteBubblePhoto(photoId: Int): Flow<String> {
        return flow {
            Log.d(TAG, "deleteBubblePhoto: Processing...")
            emit("Processing...")
            try {
                val response = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_photos")
                        .delete {
                            filter {
                                eq("id", photoId)
                            }
                        }
                }
                Log.d(TAG, "deleteBubblePhoto: Bubble photo successfully deleted")
                emit("Bubble photo successfully deleted")
            } catch (e: Exception) {
                Log.e(TAG, "deleteBubblePhoto: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }
}
