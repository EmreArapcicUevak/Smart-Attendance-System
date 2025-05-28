package com.example.smartattendancesystemandroid.data.model

data class CourseResponse(
    val courseId: Long,
    val courseName: String,
    val courseFaculty: String,
    val courseCode: String,
    val hasTutorial: Boolean,
    val hasLab: Boolean
)
