package main.kotlin.com.smartattendance.dto

data class UserRequest(
    val organizationId: Long,
    val email: String,
    val password: String,
)