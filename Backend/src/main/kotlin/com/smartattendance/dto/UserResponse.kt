package com.smartattendance.dto

import com.smartattendance.entity.Role


data class UserResponse (
    val id: Long,
    val email: String,
    val fullName: String,
    val role: Role,
)

data class ToStudentResponse(
    val studentId: Long,
    val fullName: String,
    val role: Role
)