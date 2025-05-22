package com.example.smartattendancesystemandroid.auth

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}