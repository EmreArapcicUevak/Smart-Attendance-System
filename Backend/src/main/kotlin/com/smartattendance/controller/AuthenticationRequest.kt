package main.kotlin.com.smartattendance.controller

data class AuthenticationRequest(
    val fullName: String,
    val email: String,
    val password: String,
)
