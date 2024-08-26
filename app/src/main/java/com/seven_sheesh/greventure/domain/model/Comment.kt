package com.seven_sheesh.greventure.domain.model

data class Comment(
    val id: String,
    val threadId: String? = null,
    val userId: String? = null,
    val content: String,
    val createdAt: String? = null
)