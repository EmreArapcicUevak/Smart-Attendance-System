package com.example.smartattendancesystemandroid.auth

import android.util.Log
import com.example.smartattendancesystemandroid.data.token.TokenProvider
import retrofit2.HttpException

class AuthRepositoryImplementation(
    private val api: AuthApi,
    private val tokenProvider: TokenProvider
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): AuthResult<Unit> {
        return try {
            val response = api.login(
                request = AuthRequest(email = email, password = password)
            )
            tokenProvider.setToken(response.accessToken)

            AuthResult.Authorized()
        }
        catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            }
            else {
                Log.e("APIE", e.toString())
                AuthResult.UnknownError()
            }
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = tokenProvider.getToken() ?: return AuthResult.Unauthorized()
            api.authenticate(token.toString())
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
            Log.d("APIE", e.toString())
            AuthResult.UnknownError()
        }
    }
}