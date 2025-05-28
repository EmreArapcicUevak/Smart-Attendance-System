package com.smartattendance.dto

import com.smartattendance.entity.Attendance
import com.smartattendance.entity.ComponentType

data class AttendanceRequest(
    val studentIds: List<Long>,
    val componentType: ComponentType,
    val weekNumber: Int
)

data class AttendanceResponse(
    val id: Long,
    val courseId: Long,
    val componentType: String, // e.g., "COURSE", "TUTORIAL", "LAB"
    val weekNumber: Int,
    val status: String // e.g., "PRESENT", "ABSENT"
) {
    companion object {
        fun fromEntity(attendance: Attendance) = AttendanceResponse(
            id = attendance.id,
            courseId = attendance.course?.id ?: 0,
            componentType = attendance.componentType.name,
            weekNumber = attendance.weekNumber,
            status = attendance.status.name
        )
    }
}

data class AttendanceStatusResponse(
    val studentId: Long,
    val componentType: String,
    val weekNumber: Int,
    val status: String
) {
    companion object {
        fun fromEntity(attendance: Attendance) = AttendanceStatusResponse(
            studentId = attendance.studentId,
            componentType = attendance.componentType.name,
            weekNumber = attendance.weekNumber,
            status = attendance.status.name
        )
    }}
