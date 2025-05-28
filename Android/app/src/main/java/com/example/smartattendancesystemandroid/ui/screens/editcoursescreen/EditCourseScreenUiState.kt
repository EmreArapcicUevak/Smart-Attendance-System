package com.example.smartattendancesystemandroid.ui.screens.editcoursescreen

data class EditCourseScreenUiState(
    val isLoading: Boolean = false,
    val courseId: Long = -1,
    val courseCode: String = "",
    val selectedTab: EditCourseScreenTab = EditCourseScreenTab.MAIN,
    val courseName: String = "",
    val courseFaculty: String = "",
    val hasTutorials: Boolean = false,
    val hasLabs: Boolean = false,
    val canUpdate: Boolean = false,
    val enrollFieldValue: String = "",
    val withdrawFieldValue: String = "",
    val canEnroll: Boolean = false,
    val canWithdraw: Boolean = false,
    val closeScreen: Boolean = false,
    val isDialogOpen: Boolean = false
)
