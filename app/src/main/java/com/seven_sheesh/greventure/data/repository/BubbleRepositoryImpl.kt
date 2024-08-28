package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.repository.BubbleRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class BubbleRepositoryImpl @Inject constructor(private val supabaseClientProvider: Postgrest) : BubbleRepository {

    private val TAG = "BubbleRepositoryImpl"

    override fun getAllBubbles(): Flow<Pair<String, List<Bubble>>> {
        return flow {
            Log.d(TAG, "getAllBubbles: Loading...")
            emit(Pair("Loading...", emptyList<Bubble>()))
            try {
                val bubblesResponse = supabaseClientProvider.from("bubbles").select().decodeList<Bubble>()
                val bubbles = bubblesResponse ?: emptyList<Bubble>()
                Log.d(TAG, "getAllBubbles: Successfully fetched bubbles")
                emit(Pair("Successfully fetched bubbles", bubbles))
            } catch (e: Exception) {
                Log.e(TAG, "getAllBubbles: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList<Bubble>()))
            }
        }
    }

    override fun getBubbleById(bubbleId: String): Flow<Pair<String, Bubble?>> {
        return flow {
            Log.d(TAG, "getBubbleById: Loading...")
            emit(Pair("Loading...", null))
            try {
                val bubbleResponse = supabaseClientProvider.from("bubbles")
                    .select {
                        filter {
                            eq("id", bubbleId)
                        }
                    }
                    .decodeSingleOrNull<Bubble>()
                Log.d(TAG, "getBubbleById: Bubble found")
                emit(Pair("Bubble found", bubbleResponse))
            } catch (e: Exception) {
                Log.e(TAG, "getBubbleById: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun upsertBubble(bubble: Bubble): Flow<String> {
        return flow {
            Log.d(TAG, "upsertBubble: Processing...")
            emit("Processing...")
            try {
                val response = supabaseClientProvider.from("bubbles").upsert(bubble)
                Log.d(TAG, "upsertBubble: Bubble successfully inserted/updated")
                emit("Bubble successfully inserted/updated")
            } catch (e: Exception) {
                Log.e(TAG, "upsertBubble: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun deleteBubble(bubbleId: String): Flow<String> {
        return flow {
            Log.d(TAG, "deleteBubble: Processing...")
            emit("Processing...")
            try {
                val response = supabaseClientProvider.from("bubbles")
                    .delete {
                        filter {
                            eq("id", bubbleId)
                        }
                    }
                Log.d(TAG, "deleteBubble: Bubble successfully deleted")
                emit("Bubble successfully deleted")
            } catch (e: Exception) {
                Log.e(TAG, "deleteBubble: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun searchBubbles(query: String): Flow<Pair<String, List<Bubble>>> {
        return flow {
            Log.d(TAG, "searchBubbles: Searching...")
            emit(Pair("Searching...", emptyList<Bubble>()))
            try {
                val bubblesResponse = supabaseClientProvider.from("bubbles")
                    .select {
                        filter {
                            eq("description.ilike", "%$query%")
                        }
                    }
                    .decodeList<Bubble>()
                val bubbles = bubblesResponse ?: emptyList<Bubble>()
                Log.d(TAG, "searchBubbles: Search completed successfully")
                emit(Pair("Search completed successfully", bubbles))
            } catch (e: Exception) {
                Log.e(TAG, "searchBubbles: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList<Bubble>()))
            }
        }
    }
}
