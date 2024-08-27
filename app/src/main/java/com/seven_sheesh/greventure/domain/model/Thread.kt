package com.seven_sheesh.greventure.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thread(
    @SerialName("id") val id: String,
    @SerialName("bubble_id") val bubbleId: String? = null,
    @SerialName("user_id") val userId: String? = null,
    @SerialName("content") val content: String,
    @SerialName("created_at") val createdAt: String? = null
)