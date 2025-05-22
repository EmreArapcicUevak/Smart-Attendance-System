package com.example.smartattendancesystemandroid.utils

import android.util.Base64
import android.util.Log
import com.example.smartattendancesystemandroid.data.model.JwtPayload
import com.example.smartattendancesystemandroid.data.model.UserType
import org.json.JSONObject

fun decodeJwtPayload(token: String): JwtPayload? {
    try {
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