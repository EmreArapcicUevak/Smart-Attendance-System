package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

data class StudentCardData(
    val id: String,
    val name: String,
    val studentId: String,
    val lectureSection: Int,
    val tutorialSection: Int?,
    val labSection: Int?,
    val lectureAttendancePercentage: Double,
    val tutorialAttendancePercentage: Double?,
    val labAttendancePercentage: Double?,
    val lectureAttendanceMinPercentage: Double,
    val tutorialAttendanceMinPercentage: Double?,
    val labAttendanceMinPercentage: Double?,
)
