package com.example.smartattendancesystemandroid.ui.screens.markattendancescreen

import com.example.smartattendancesystemandroid.data.model.CourseType

data class MarkAttendanceScreenUiState(
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
    val courseId: Long = -1,
    val week: Int = 1,
    val selectedComponent: CourseType = CourseType.LECTURE,
    val sliderPosition: Float = 1f,
    val sliderPositionInt: Int = 1,
    val markAttendanceState: MarkAttendanceState = MarkAttendanceState.PRE,
    val studentList: List<Long> = listOf<Long>()
)
