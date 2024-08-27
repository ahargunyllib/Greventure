package com.seven_sheesh.greventure.data.repository

import com.seven_sheesh.greventure.data.remote.SupabaseClientProvider
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.repository.BubbleRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers

class BubbleRepositoryImpl(private val supabaseClientProvider: SupabaseClientProvider) : BubbleRepository {
    override fun getAllBubbles(): Flow<Pair<String, List<Bubble>>> {
        return flow {
            emit(Pair("Loading...", emptyList<Bubble>()))
            try {
                val bubblesResponse = supabaseClientProvider.getClient().postgrest.from("bubbles").select().decodeList<Bubble>()
                val bubbles = bubblesResponse ?: emptyList<Bubble>()
                emit(Pair("Successfully fetched bubbles", bubbles))
            } catch (e: Exception) {
                emit(Pair("An error occurred: ${e.message}", emptyList<Bubble>()))
            }
        }
    }

    override fun getBubbleById(bubbleId: String): Flow<Pair<String, Bubble?>> {
        return flow {
            emit(Pair("Loading...", null))
            try {
                val bubbleResponse = supabaseClientProvider.getClient().postgrest.from("bubbles")
                    .select {
                        filter {
                            eq("id", bubbleId)
                        }
                    }
                    .decodeSingleOrNull<Bubble>()
                emit(Pair("Bubble found", bubbleResponse))
            } catch (e: Exception) {
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun upsertBubble(bubble: Bubble): Flow<String> {
        return flow {
            emit("Processing...")
            try {
                val response = supabaseClientProvider.getClient().postgrest.from("bubbles").upsert(bubble)
                emit("Bubble successfully inserted/updated")
            } catch (e: Exception) {
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun deleteBubble(bubbleId: String): Flow<String> {
        return flow {
            emit("Processing...")
            try {
                val response = supabaseClientProvider.getClient().postgrest.from("bubbles")
                    .delete {
                        filter {
                            eq("id", bubbleId)
                        }
                    }
                emit("Bubble successfully deleted")
            } catch (e: Exception) {
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun searchBubbles(query: String): Flow<Pair<String, List<Bubble>>> {
        return flow {
            emit(Pair("Searching...", emptyList<Bubble>()))
            try {
                val bubblesResponse = supabaseClientProvider.getClient().postgrest.from("bubbles")
                    .select {
                        filter {
                            eq("description.ilike", "%$query%")
                        }
                    }
                    .decodeList<Bubble>()
                val bubbles = bubblesResponse ?: emptyList<Bubble>()
                emit(Pair("Search completed successfully", bubbles))
            } catch (e: Exception) {
                emit(Pair("An error occurred: ${e.message}", emptyList<Bubble>()))
            }
        }
    }
}
