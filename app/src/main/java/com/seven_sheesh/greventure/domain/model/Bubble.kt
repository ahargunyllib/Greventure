package com.seven_sheesh.greventure.domain.model

import java.math.BigDecimal

data class Bubble(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val type: BubbleType,
    val eventType: EventType? = null,
    val latitude: Double,
    val longitude: Double,
    val startTime: String,
    val duration: String,
    val phoneNumber: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

data class BubblePhoto(
    val id: Int,
    val bubbleId: String,
    val url: String,
    val createdAt: String? = null
)

data class BubbleSocialMedia(
    val id: Int,
    val bubbleId: String,
    val content: String,
    val type: SocialMedia,
    val createdAt: String? = null
)