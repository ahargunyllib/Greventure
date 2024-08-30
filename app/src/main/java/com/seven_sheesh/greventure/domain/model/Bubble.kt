package com.seven_sheesh.greventure.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class Bubble(
    @SerialName("id") val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("type") val type: BubbleType,
    @SerialName("event_type") val eventType: EventType? = null,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("start_time") val startTime: String,
    @SerialName("duration") val duration: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("photo_url") val photoUrl: String? = null,
    @SerialName("average_rating") val averageRating: Double? = null,
){
    fun isComplete(): Boolean {
        return id.isNotBlank() &&
                userId.isNotBlank() &&
                title.isNotBlank() &&
                description.isNotBlank() &&
                startTime.isNotBlank() &&
                duration.isNotBlank() &&
                phoneNumber.isNotBlank() &&
                latitude != 0.0 &&
                longitude != 0.0
    }
}

@Serializable
data class BubblePhoto(
    @SerialName("id") val id: Int,
    @SerialName("bubble_id") val bubbleId: String,
    @SerialName("url") val url: String,
    @SerialName("created_at") val createdAt: String? = null
) {
    fun isComplete(): Boolean {
        return id != 0 &&
                bubbleId.isNotBlank()
    }
}


@Serializable
data class BubbleSocialMedia(
    @SerialName("id") val id: Int,
    @SerialName("bubble_id") val bubbleId: String,
    @SerialName("content") val content: String,
    @SerialName("type") val type: SocialMedia,
    @SerialName("created_at") val createdAt: String? = null
) {
    fun isComplete(): Boolean {
        return id != 0 &&
                bubbleId.isNotBlank() &&
                content.isNotBlank()
    }
}