package com.seven_sheesh.greventure.domain.model

data class Bookmark(
    val id: String,
    val userId: String? = null,
    val bubbleId: String? = null,
    val isStarted: Boolean = false,
    val createdAt: String? = null
)