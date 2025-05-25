package com.example.smartattendancesystemandroid.data.model

data class AttendanceRequest(
    val courseId: Long,
    val week: Int,
    val courseType: CourseType,
    val status: WeekAttendedState,
    val attendees: List<Long> // list of length 1 is used for modifying attendance of a student
)
