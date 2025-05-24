package com.example.smartattendancesystemandroid.data.token

import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import androidx.core.content.edit
import com.example.smartattendancesystemandroid.data.model.JwtPayload
import com.example.smartattendancesystemandroid.data.model.UserType
import org.json.JSONObject

class TokenProviderImplementation (
    private val prefs: SharedPreferences
): TokenProvider {
    override fun getToken(): String? {
        return prefs.getString("jwt", null)
    }

    override fun setToken(token: String) {
        prefs.edit { putString("jwt", token) }
    }

    override fun decodeTokenPayload(token: String?): JwtPayload? {
        try {
            if (token == null) return null

            val payload = token.split(".")[1]
            val decodedPayload = Base64.decode(payload, Base64.URL_SAFE)
            val jsonString = String(decodedPayload, Charsets.UTF_8)

            val json = JSONObject(jsonString)

            json.let { payload ->

                val role = when(payload.getString("role")) {
                    "ADMIN" -> UserType.ADMIN
                    "TEACHER" -> UserType.TEACHER
                    "STUDENT" -> UserType.STUDENT
                    else -> {
                        throw error("Invalid role from jwt")
                        return null
                    }
                }

                return JwtPayload(
                    sub = payload.getString("sub"),
                    fullName = payload.getString("fullName"),
                    iat = payload.getLong("iat"),
                    exp = payload.getLong("exp"),
                    role = role
                )
            }
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            return null
        }
    }

    override fun removeToken() {
        prefs.edit { remove("jwt") }
    }
}