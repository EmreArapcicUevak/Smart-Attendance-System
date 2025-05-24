package com.example.smartattendancesystemandroid.data.model

data class AttendanceResponse(
    val lecture: List<WeekAttendedState>,
    val tutorial: List<WeekAttendedState>? = null,
    val lab: List<WeekAttendedState>? = null
)
