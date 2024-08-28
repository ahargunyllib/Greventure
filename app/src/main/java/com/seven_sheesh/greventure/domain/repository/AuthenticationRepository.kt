package com.seven_sheesh.greventure.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Flow<String>
    suspend fun signUp(name: String, email: String, phoneNumber: String, password: String): Flow<String>
    suspend fun signInWithNativeGoogle(googleIdToken: String, rawNonce: String): Flow<String>
}
