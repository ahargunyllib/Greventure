package com.seven_sheesh.greventure.data.repository

import android.util.Log
import com.seven_sheesh.greventure.domain.model.User
import com.seven_sheesh.greventure.domain.repository.UserRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val supabaseClientProvider: Postgrest) :
    UserRepository {

    private val TAG = "UserRepositoryImpl"

    override fun getAllUsers(): Flow<Pair<String, List<User>>> {
        return flow {
            Log.d(TAG, "getAllUsers: Loading...")
            emit(Pair("Loading...", emptyList<User>()))
            try {
                val usersResponse = supabaseClientProvider.from("users").select().decodeList<User>()
                val users = usersResponse ?: emptyList<User>()
                Log.d(TAG, "getAllUsers: Successfully fetched users")
                emit(Pair("Successfully fetched users", users))
            } catch (e: Exception) {
                Log.e(TAG, "getAllUsers: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", emptyList<User>()))
            }
        }
    }

    override fun getUserById(userId: String): Flow<Pair<String, User?>> {
        return flow {
            Log.d(TAG, "getUserById: Loading...")
            emit(Pair("Loading...", null))
            try {
                val userResponse = supabaseClientProvider.from("users")
                    .select {
                        filter {
                            eq("id", userId)
                        }
                    }
                    .decodeSingleOrNull<User>()
                Log.d(TAG, "getUserById: User found")
                emit(Pair("User found", userResponse))
            } catch (e: Exception) {
                Log.e(TAG, "getUserById: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun getUserByEmail(email: String): Flow<Pair<String, User?>> {
        return flow {
            Log.d(TAG, "getUserByEmail: Loading...")
            emit(Pair("Loading...", null))
            try {
                val userResponse = supabaseClientProvider.from("users")
                    .select {
                        filter {
                            eq("email", email)
                        }
                    }
                    .decodeSingleOrNull<User>()
                Log.d(TAG, "getUserByEmail: User found")
                emit(Pair("User found", userResponse))
            } catch (e: Exception) {
                Log.e(TAG, "getUserByEmail: An error occurred: ${e.message}", e)
                emit(Pair("An error occurred: ${e.message}", null))
            }
        }
    }

    override fun upsertUser(user: User): Flow<String> {
        return flow {
            Log.d(TAG, "upsertUser: Processing...")
            emit("Processing...")
            try {
                supabaseClientProvider.from("users").upsert(user)
                Log.d(TAG, "upsertUser: User successfully inserted/updated")
                emit("User successfully inserted/updated")
            } catch (e: Exception) {
                Log.e(TAG, "upsertUser: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }

    override fun deleteUser(userId: String): Flow<String> {
        return flow {
            Log.d(TAG, "deleteUser: Processing...")
            emit("Processing...")
            try {
                supabaseClientProvider.from("users")
                    .delete {
                        filter {
                            eq("id", userId)
                        }
                    }
                Log.d(TAG, "deleteUser: User successfully deleted")
                emit("User successfully deleted")
            } catch (e: Exception) {
                Log.e(TAG, "deleteUser: An error occurred: ${e.message}", e)
                emit("An error occurred: ${e.message}")
            }
        }
    }
}
