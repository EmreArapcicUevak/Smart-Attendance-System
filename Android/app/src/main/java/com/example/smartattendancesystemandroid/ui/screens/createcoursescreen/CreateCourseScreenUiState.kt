package com.example.smartattendancesystemandroid.ui.screens.createcoursescreen

data class CreateCourseScreenUiState(
    val isLoading: Boolean = false,
    val courseName: String = "",
    val courseCode: String = "",
    val courseFaculty: String = "",
    val hasTutorials: Boolean = false,
    val hasLabs: Boolean = false,
    val canCreate: Boolean = false,
    val closeScreen: Boolean = false,
)
