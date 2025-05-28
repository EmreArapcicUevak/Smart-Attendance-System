package com.example.smartattendancesystemandroid.ui.screens.checkstudentcourseattendancescreen

import com.example.smartattendancesystemandroid.data.model.WeekAttendedState

data class CheckStudentCourseAttendanceScreenUiState(
    val isLoading: Boolean = true,
    val studentName: String = "",
    val studentId: Long = -1,
    val courseId: Long = -1,
    val lectureWeekAttendedStateList: List<WeekAttendedState> = listOf<WeekAttendedState>(),
    val tutorialWeekAttendedStateList: List<WeekAttendedState>? = null,
    val labWeekAttendedStateList: List<WeekAttendedState>? = null,
    val dialogOpen: Boolean = false,
    val lastClickedWeekState: WeekAttendedState = WeekAttendedState.NOT_MARKED,
    val dialogSelectedWeek: Int? = null,
    val dialogSelectedCourseComponent: String? = null
)
