package com.seven_sheesh.greventure.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bookmark(
    @SerialName("id") val id: String,
    @SerialName("user_id") val userId: String? = null,
    @SerialName("bubble_id") val bubbleId: String? = null,
    @SerialName("is_started") val isStarted: Boolean = false,
    @SerialName("created_at") val createdAt: String? = null
)