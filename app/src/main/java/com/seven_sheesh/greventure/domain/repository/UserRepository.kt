package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<Pair<String, List<User>>>
    fun getUserById(userId: String): Flow<Pair<String, User?>>
    fun getUserByEmail(email: String): Flow<Pair<String, User?>>
    fun upsertUser(user: User): Flow<String>
    fun deleteUser(userId: String): Flow<String>
}
