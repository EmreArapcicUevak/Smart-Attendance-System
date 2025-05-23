package com.smartattendance.dto

import com.smartattendance.entity.DayOfTheWeek

data class CourseRequest(
    val courseName: String,
    val courseCode: String,
    val dayOfTheWeek: DayOfTheWeek,
)

data class CourseResponse(
    val id: Long,
    val courseName: String,
    val courseCode: String,
    val dayOfTheWeek: DayOfTheWeek,
)