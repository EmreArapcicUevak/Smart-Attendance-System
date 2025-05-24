package com.example.smartattendancesystemandroid.ui.screens.studenthomescreen


data class StudentHomeScreenUiState(
    val isLoading: Boolean = false,
    val studentCourses: List<StudentCourseCardData> = listOf<StudentCourseCardData>()
)
