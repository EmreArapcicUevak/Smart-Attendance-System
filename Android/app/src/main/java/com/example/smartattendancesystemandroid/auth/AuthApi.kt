package com.example.smartattendancesystemandroid.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body request: AuthRequest
    ): TokenResponse

    @POST("auth/validate-token")
    suspend fun authenticate(
        @Body token: String
    )
}