package com.example.smartattendancesystemandroid.auth

import android.content.SharedPreferences
import retrofit2.HttpException
import androidx.core.content.edit

class AuthRepositoryImplementation(
    private val api: AuthApi,
    private val prefs: SharedPreferences
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): AuthResult<Unit> {
        return try {
            val response = api.login(
                request = AuthRequest(email = email, password = password)
            )
            prefs.edit() { putString("jwt", response.token) }
            AuthResult.Authorized()
        }
        catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            }
            else {
                AuthResult.UnknownError()
            }
        }
        catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate("Bearer $token")
            AuthResult.Authorized()
        }
        catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            }
            else {
                AuthResult.UnknownError()
            }
        }
        catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}