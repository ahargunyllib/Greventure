package com.seven_sheesh.greventure.data.repository

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import com.seven_sheesh.greventure.domain.model.BubblePhoto
import com.seven_sheesh.greventure.domain.repository.BubblePhotoRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

class BubblePhotoRepositoryImpl @Inject constructor(
    private val supabaseClientProvider: Postgrest,
    private val supabaseStorage: Storage,
    private val application: Application
) : BubblePhotoRepository {

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
            emit("Starting photo upload...")
            Log.d(TAG, "upsertBubblePhoto: Uploading photo...")

            val contentResolver: ContentResolver = application.contentResolver
            val uri = Uri.parse(bubblePhoto.url)
            val inputStream: InputStream? = contentResolver.openInputStream(uri)

            if (inputStream == null) {
                Log.e(TAG, "upsertBubblePhoto: Unable to open InputStream from Uri")
                emit("Unable to open InputStream from Uri")
                return@flow
            }

            try {
                val imagePath = "bubble_photos/${uri.lastPathSegment ?: "unknown_image"}"
                val uploadResponse = supabaseStorage.from("bubble").upload(
                    path = imagePath,
                    data = inputStream.readBytes(),
                    upsert = true
                )
                Log.d(TAG, "upsertBubblePhoto: Upload response: $uploadResponse")

                val publicUrl = supabaseStorage.from("bubble").publicUrl(imagePath)
                Log.d(TAG, "upsertBubblePhoto: Public URL: $publicUrl")

                val updatedBubblePhoto = bubblePhoto.copy(url = publicUrl)
                val updateResponse = withContext(Dispatchers.IO) {
                    supabaseClientProvider.from("bubble_photos").upsert(updatedBubblePhoto)
                }
                Log.d(TAG, "upsertBubblePhoto: Update response: $updateResponse")

                Log.d(TAG, "upsertBubblePhoto: Bubble photo successfully inserted/updated")
                emit("Bubble photo successfully inserted/updated")
            } catch (e: Exception) {
                Log.e(TAG, "upsertBubblePhoto: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            } finally {
                inputStream.close()
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
