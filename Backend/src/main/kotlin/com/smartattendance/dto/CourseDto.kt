package main.kotlin.com.smartattendance.dto

import main.kotlin.com.smartattendance.entity.DayOfTheWeek

data class CourseRequest(
    val courseName: String,
    val courseCode: String,
    val dayOfTheWeek: DayOfTheWeek,
)

data class CourseResponse(
    val id: Long
)