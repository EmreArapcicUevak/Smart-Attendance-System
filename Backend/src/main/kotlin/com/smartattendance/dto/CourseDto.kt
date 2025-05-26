package com.smartattendance.dto

import com.smartattendance.entity.DayOfTheWeek

data class CourseRequest(
    val courseName: String,
    val courseCode: String,
    var faculty: String,
    val hasLabs: Boolean,
    val hasTutorials: Boolean
)

data class CourseResponse(
    val id: Long,
    val courseName: String,
    val courseCode: String,
    val faculty: String,
)