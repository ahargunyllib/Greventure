package com.seven_sheesh.greventure.domain.model

data class BubbleType(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val description: String = "",
    val type: String = "", // e.g., Event, Place, Activity, Discussion
    val latitude: Double = 0.0, // Latitude of the bubble location
    val longitude: Double = 0.0, // Longitude of the bubble location
    val color: String = "", // Color representing the type of activity
    val startTime: String = "",
    val duration: String = "",
    val createdAt: String = "",
    val updatedAt: String = ""
)

object DummyBubble {
    val bubble = BubbleType(
        id = "1",
        userId = "1",
        title = "Morning Yoga Session",
        description = "Join us for a refreshing morning yoga session at the park.",
        type = "Activity",
        latitude = -7.9545,
        longitude = 112.6155,
        color = "Blue",
        startTime = "2023-01-01 08:00:00",
        duration = "01:30:00",
        createdAt = "2023-01-01 07:00:00",
        updatedAt = "2023-01-01 08:00:00"
    )
}