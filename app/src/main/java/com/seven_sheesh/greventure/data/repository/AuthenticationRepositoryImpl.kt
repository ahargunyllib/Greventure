package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.User
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import okhttp3.internal.wait
import javax.inject.Inject
import com.google.gson.JsonObject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth,
    private val db: Postgrest
) : AuthenticationRepository {
    private val FILE_NAME = "AuthenticationRepositoryImpl"

    override suspend fun signIn(email: String, password: String): Flow<String> {
        val FUNCTION_NAME = "signIn"

        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit("Loading")
            try {
                auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Success")
                emit("Successfully login")
            } catch (e: Exception) {
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                emit("Error")
            }
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        phoneNumber: String,
        password: String
    ): Flow<String> {
        val FUNCTION_NAME = "signUp"

        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit("Loading")
            try {
                auth.signUpWith(Email) {
                    this.email = email
                    this.password = password
                }
                withContext(Dispatchers.IO) {
                    auth.awaitInitialization()
                    val currentUser = auth.currentUserOrNull()
                    if (currentUser == null) {
                        Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                        emit("User not found")
                        return@withContext
                    }

                    val userDto = User(
                        id = currentUser.id,
                        name = name,
                        email = email,
                        phoneNum = phoneNumber
                    )
                    db.from("users").insert(userDto)
                }
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Success")
                emit("Successfully register")
            } catch (e: Exception) {
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override suspend fun signInWithNativeGoogle(
        googleIdToken: String,
        rawNonce: String
    ): Flow<String> {
        val FUNCTION_NAME = "signInWithNativeGoogle"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit("Loading")
            try {
                auth.signInWith(IDToken) {
                    idToken = googleIdToken
                    provider = Google
                    nonce = rawNonce
                }
                withContext(Dispatchers.IO) {
                    auth.awaitInitialization()
                    val currentUser = auth.currentUserOrNull()
                    if (currentUser == null) {
                        Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                        emit("User not found")
                        return@withContext
                    }

                    val user = db.from("users").select {
                        filter {
                            eq("email", currentUser.email!!)
                        }
                    }.decodeSingleOrNull<User>()
                    if (user == null) {
                        val userDto = User(
                            id = currentUser.id,
                            name = currentUser.userMetadata?.get("full_name")?.toString()!!
                                .replace("\"", ""),
                            email = currentUser.email!!,
                            phoneNum = "",
                            profilePictureUrl = currentUser.userMetadata?.get("avatar_url")
                                ?.toString()!!.replace("\"", ""),
                        )

                        db.from("users").insert(userDto)
                    }
                }
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Success")
                emit("Successfully login with google")
            } catch (e: Exception) {
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override suspend fun me(): Flow<Pair<String, User?>> {
        val FUNCTION_NAME = "me"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit(Pair("Loading", null))
            try {
                auth.awaitInitialization()
                val session = auth.currentSessionOrNull()
                if (session == null) {
                    Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                    emit(Pair("User not found", null))
                    return@flow
                }

                val userEmail = session.user?.email!!
                val user = withContext(Dispatchers.IO) {
                    db.from("users").select {
                        filter {
                            eq("email", userEmail)
                        }
                    }.decodeSingle<User>()
                }

                Log.d(FILE_NAME, "[$FUNCTION_NAME] Success")
                emit(Pair("Successfully get user", user))
            } catch (e: Exception) {
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override suspend fun signOut(): Flow<String> {
        val FUNCTION_NAME = "signOut"
        return flow {
            Log.d(FILE_NAME, "[$FUNCTION_NAME] Loading")
            emit("Loading")
            try {
                auth.signOut()
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Success")
                emit("Successfully sign out")
            } catch (e: Exception) {
                Log.d(FILE_NAME, "[$FUNCTION_NAME] Error")
                emit("An error occurred: ${e.message}")
            }
        }
    }
}
