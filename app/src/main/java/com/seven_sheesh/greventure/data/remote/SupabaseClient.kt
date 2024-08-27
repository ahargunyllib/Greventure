package com.seven_sheesh.greventure.data.remote

import com.seven_sheesh.greventure.utils.SUPABASE_ANON_KEY
import com.seven_sheesh.greventure.utils.SUPABASE_URL
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage


object SupabaseClientProvider {
    private val client = createSupabaseClient(
        supabaseKey = SUPABASE_ANON_KEY,
        supabaseUrl = SUPABASE_URL
    ) {
        install(Auth) {
            alwaysAutoRefresh = false
            autoLoadFromStorage = false
        }
        install(Storage)
        install(Postgrest)
    }

    fun getClient() = client
}