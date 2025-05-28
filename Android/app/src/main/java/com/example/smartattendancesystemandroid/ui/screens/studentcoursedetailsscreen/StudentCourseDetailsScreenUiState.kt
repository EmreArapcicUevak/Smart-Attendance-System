package com.example.smartattendancesystemandroid.ui.screens.studentcoursedetailsscreen

import com.example.smartattendancesystemandroid.data.model.WeekAttendedState

data class StudentCourseDetailsScreenUiState(
    val isLoading: Boolean = true,
    val courseName: String = "",
    val studentId: Long = -1,
    val courseId: Long = -1,
    val lectureWeekAttendedStateList: List<WeekAttendedState> = listOf<WeekAttendedState>(),
    val tutorialWeekAttendedStateList: List<WeekAttendedState>? = null,
    val labWeekAttendedStateList: List<WeekAttendedState>? = null,
)
