package com.seven_sheesh.greventure.domain.model

data class CommentType(
    val id: String = "",
    val threadId: String = "",
    val userId: String = "",
    val content: String = "",
    val createdAt: String = "",
    val updatedAt: String = ""
)

object DummyComment {
    val comment = CommentType(
        id = "1",
        threadId = "1",
        userId = "2",
        content = "Me too! Can't wait!",
        createdAt = "2023-01-01 07:45:00",
        updatedAt = "2023-01-01 08:45:00"
    )
}