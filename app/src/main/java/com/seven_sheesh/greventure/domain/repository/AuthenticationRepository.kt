package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Flow<String>
    suspend fun signUp(name: String, email: String, phoneNumber: String, password: String): Flow<String>
    suspend fun signInWithNativeGoogle(googleIdToken: String, rawNonce: String): Flow<String>
    suspend fun me(): Flow<Pair<String, User?>>
    suspend fun signOut(): Flow<String>
}
