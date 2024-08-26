package com.seven_sheesh.greventure.domain.repository

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signInWithNativeGoogle(googleIdToken: String, rawNonce: String): Boolean
}
