package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

data class CourseDetailsUiState(
    val isLoading: Boolean = true,
    val courseId: Long = -1,
    val courseCode: String = "",
    val students: List<StudentCardData> = listOf<StudentCardData>(),
    val filteredStudents: List<StudentCardData> = listOf<StudentCardData>(),
    val filterFieldValue: String = "",
    val canTakeAttendance: Boolean = false
)
