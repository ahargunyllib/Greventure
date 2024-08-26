package com.seven_sheesh.greventure.domain.model

data class UserType(
    val id: String = "",
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNum: String = "",
    val profilePictureUrl: String = "",
    val createdAt: String = "",
    val updatedAt: String = ""
)

object DummyUser {
    val user = UserType(
        id = "1",
        username = "jdoe",
        name = "John Doe",
        email = "jdoe@example.com",
        phoneNum = "+1234567890",
        profilePictureUrl = "http://example.com/johndoe.jpg",
        createdAt = "2023-01-01 10:00:00",
        updatedAt = "2023-01-02 12:00:00"
    )
}