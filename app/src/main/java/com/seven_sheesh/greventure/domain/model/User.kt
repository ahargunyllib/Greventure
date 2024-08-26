package com.seven_sheesh.greventure.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNum: String,
    val profilePictureUrl: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)