package com.example.smartattendancesystemandroid.data.model

data class JwtPayload(
    val sub: String,
    val fullName: String,
    val iat: Long,
    val exp: Long,
    val role: UserType,
    val id: Long
)
