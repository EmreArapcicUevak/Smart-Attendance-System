package com.smartattendance.dto

data class CourseRequest(
    val courseName: String,
    val courseCode: String,
    val hasLabs: Boolean,
    val hasTutorials: Boolean
)

data class CourseResponse(
    val id: Long,
    val courseName: String,
    val courseCode: String,
)