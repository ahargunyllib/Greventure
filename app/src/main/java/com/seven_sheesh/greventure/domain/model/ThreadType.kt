package com.seven_sheesh.greventure.domain.model

data class ThreadType(
    val id: String = "",
    val bubbleId: String = "",
    val userId: String = "",
    val content: String = "",
    val createdAt: String = "",
    val updatedAt: String = ""
)

object DummyThread {
    val thread = ThreadType(
        id = "1",
        bubbleId = "1",
        userId = "1",
        content = "Looking forward to the yoga session!",
        createdAt = "2023-01-01 07:30:00",
        updatedAt = "2023-01-01 08:30:00"
    )
}