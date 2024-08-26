package com.seven_sheesh.greventure.domain.model

data class BookmarkType(
    val id: String = "",
    val userId: String = "",
    val bubbleId: String = "",
    val createdAt: String = ""
)

object DummyBookmark {
    val bookmark = BookmarkType(
        id = "1",
        userId = "1",
        bubbleId = "1",
        createdAt = "2023-01-01 09:00:00"
    )
}