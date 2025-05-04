package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

data class CourseDetailsUiState(
    val courseCode: String = "",
    val filteredStudents: List<StudentCardData> = listOf<StudentCardData>(),
    val filterFieldValue: String = "",
    val canTakeAttendance: Boolean = false
)
