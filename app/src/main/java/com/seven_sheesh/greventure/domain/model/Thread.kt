package com.seven_sheesh.greventure.domain.model

data class Thread(
    val id: String,
    val bubbleId: String? = null,
    val userId: String? = null,
    val content: String,
    val createdAt: String? = null
)