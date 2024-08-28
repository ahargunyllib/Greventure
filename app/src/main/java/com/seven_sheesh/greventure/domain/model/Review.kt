package com.seven_sheesh.greventure.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Review(
    @SerialName("id") val id: Int,
    @SerialName("user_id") val userId: String,
    @SerialName("bubble_id") val bubbleId: String,
    @SerialName("star") val star: Int,
    @SerialName("content") val content: String,
    @SerialName("created_at") val createdAt: String? = null
)