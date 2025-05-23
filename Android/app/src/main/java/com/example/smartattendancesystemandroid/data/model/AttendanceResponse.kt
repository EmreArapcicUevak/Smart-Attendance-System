package com.example.smartattendancesystemandroid.data.model

data class AttendanceResponse(
    val lecture: List<WeekAttendedState>,
    val tutorial: List<WeekAttendedState>?,
    val lab: List<WeekAttendedState>?
)
