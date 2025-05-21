package main.kotlin.com.smartattendance.dto

import main.kotlin.com.smartattendance.entity.Role

data class UserRequest(
    val organizationId: Long,
    val fullName: String,
    val email: String,
    val password: String,
    val role: Role,
    val studentId: Long? = null,
)