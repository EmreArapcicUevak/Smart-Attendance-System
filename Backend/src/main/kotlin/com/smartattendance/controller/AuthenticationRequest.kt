package com.smartattendance.controller

data class AuthenticationRequest(
    val email: String,
    val password: String,
)
