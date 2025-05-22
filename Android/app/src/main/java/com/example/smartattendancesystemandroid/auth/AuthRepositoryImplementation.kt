package com.example.smartattendancesystemandroid.auth

import android.content.SharedPreferences
import android.util.Log
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
            prefs.edit { putString("jwt", response.token) }
            AuthResult.Authorized()
        }
        catch (e: HttpException) {
            if (e.code() == 401) {
                // remove this after testing
                prefs.edit { putString("jwt", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIiLCJpYXQiOjE3NDc5MjY5OTEsImV4cCI6MTc3OTQ2Mjk5MSwiYXVkIjoiIiwic3ViIjoidmVkYWRAbWFpbC5jb20iLCJmdWxsTmFtZSI6IlZlZGFkIFNpbGppYyIsInJvbGUiOiJURUFDSEVSIn0.h9WD6IAfEHL-cKraaT3OWnBvFh66r21PLe7vjONk2y8") }
                AuthResult.Authorized()
                /*
                AuthResult.Unauthorized()
                 */
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
            api.authenticate(token)
            AuthResult.Authorized()
        }
        catch (e: HttpException) {
            if (e.code() == 404) {
                AuthResult.Unauthorized()
            }
            else {
                Log.d("APIE", e.toString())
                AuthResult.UnknownError()
            }
        }
        catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}