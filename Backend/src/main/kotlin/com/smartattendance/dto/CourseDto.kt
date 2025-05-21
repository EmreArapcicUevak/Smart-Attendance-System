package main.kotlin.com.smartattendance.dto

data class CourseRequest(
    val courseName: String,
    val courseCode: String,
    val dayOfTheWeek: DayOfTheWeek,
)

data class CourseResponse(
    val id: Long
)