package com.example.smartattendancesystemandroid.ui.screens.staffhomescreen

data class StaffHomeScreenUiState(
    val isLoading: Boolean = false,
    val staffCourses: List<CourseCardData> = listOf<CourseCardData>()
)
