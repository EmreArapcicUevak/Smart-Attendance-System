package com.example.smartattendancesystemandroid.data.token

import com.example.smartattendancesystemandroid.data.model.JwtPayload

interface TokenProvider {
    fun getToken(): String?
    fun setToken(token: String)
    fun decodeTokenPayload(token: String?): JwtPayload?
    fun removeToken()
}