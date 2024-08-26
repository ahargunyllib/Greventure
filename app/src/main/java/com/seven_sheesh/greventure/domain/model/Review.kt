package com.seven_sheesh.greventure.domain.model

data class Review(
    val id: Int,
    val userId: String,
    val bubbleId: String,
    val star: Int,
    val content: String,
    val createdAt: String? = null
)