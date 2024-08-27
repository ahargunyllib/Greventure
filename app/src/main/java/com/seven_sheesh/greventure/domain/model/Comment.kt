package com.seven_sheesh.greventure.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("id") val id: String,
    @SerialName("thread_id") val threadId: String? = null,
    @SerialName("user_id") val userId: String? = null,
    @SerialName("content") val content: String,
    @SerialName("created_at") val createdAt: String? = null
)