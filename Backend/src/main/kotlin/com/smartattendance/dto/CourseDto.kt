package main.kotlin.com.smartattendance.dto

data class CourseRequest(
    val courseName: String,
    val courseCode: String,
)

data class CourseResponse(
    val id: Long
)