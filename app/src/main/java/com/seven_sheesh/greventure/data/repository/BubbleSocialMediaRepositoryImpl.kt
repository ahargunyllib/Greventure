package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.BubbleSocialMedia
import com.seven_sheesh.greventure.domain.repository.BubbleSocialMediaRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BubbleSocialMediaRepositoryImpl @Inject constructor(private val supabaseClientProvider: Postgrest) : BubbleSocialMediaRepository {

    private val TAG = "BubbleSocialMediaRepoImpl"

    override fun getAllBubbleSocialMedia(): Flow<Pair<String, List<BubbleSocialMedia>>> {
        return flow {
            Log.d(TAG, "getAllBubbleSocialMedia: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val bubbleSocialMediaResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_social_media").select().decodeList<BubbleSocialMedia>()
                }
                val bubbleSocialMedia = bubbleSocialMediaResponse ?: emptyList()
                Log.d(TAG, "getAllBubbleSocialMedia: Successfully fetched bubble social media")
                emit(Pair("Successfully fetched bubble social media", bubbleSocialMedia))
            } catch (e: Exception) {
                Log.e(TAG, "getAllBubbleSocialMedia: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun getBubbleSocialMediaById(socialMediaId: Int): Flow<Pair<String, BubbleSocialMedia?>> {
        return flow {
            Log.d(TAG, "getBubbleSocialMediaById: Loading...")
            emit(Pair("Loading...", null))
            try {
                val bubbleSocialMediaResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_social_media")
                        .select {
                            filter {
                                eq("id", socialMediaId)
                            }
                        }
                        .decodeSingleOrNull<BubbleSocialMedia>()
                }
                Log.d(TAG, "getBubbleSocialMediaById: Bubble social media found")
                emit(Pair("Bubble social media found", bubbleSocialMediaResponse))
            } catch (e: Exception) {
                Log.e(TAG, "getBubbleSocialMediaById: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun getBubbleSocialMediaByBubbleId(bubbleId: String): Flow<Pair<String, List<BubbleSocialMedia>>> {
        return flow {
            Log.d(TAG, "getBubbleSocialMediaByBubbleId: Loading...")
            emit(Pair("Loading...", emptyList()))
            try {
                val bubbleSocialMediaResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_social_media")
                        .select {
                            filter {
                                eq("bubble_id", bubbleId)
                            }
                        }
                        .decodeList<BubbleSocialMedia>()
                }
                val bubbleSocialMedia = bubbleSocialMediaResponse ?: emptyList()
                Log.d(TAG, "getBubbleSocialMediaByBubbleId: Successfully fetched bubble social media")
                emit(Pair("Successfully fetched bubble social media", bubbleSocialMedia))
            } catch (e: Exception) {
                Log.e(TAG, "getBubbleSocialMediaByBubbleId: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList()))
            }
        }
    }

    override fun upsertBubbleSocialMedia(bubbleSocialMedia: BubbleSocialMedia): Flow<String> {
        return flow {
            Log.d(TAG, "upsertBubbleSocialMedia: Processing...")
            emit("Processing...")
            try {
                val response = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_social_media").upsert(bubbleSocialMedia)
                }
                Log.d(TAG, "upsertBubbleSocialMedia: Bubble social media successfully inserted/updated")
                emit("Bubble social media successfully inserted/updated")
            } catch (e: Exception) {
                Log.e(TAG, "upsertBubbleSocialMedia: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun deleteBubbleSocialMedia(socialMediaId: Int): Flow<String> {
        return flow {
            Log.d(TAG, "deleteBubbleSocialMedia: Processing...")
            emit("Processing...")
            try {
                val response = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_social_media")
                        .delete {
                            filter {
                                eq("id", socialMediaId)
                            }
                        }
                }
                Log.d(TAG, "deleteBubbleSocialMedia: Bubble social media successfully deleted")
                emit("Bubble social media successfully deleted")
            } catch (e: Exception) {
                Log.e(TAG, "deleteBubbleSocialMedia: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }
}
