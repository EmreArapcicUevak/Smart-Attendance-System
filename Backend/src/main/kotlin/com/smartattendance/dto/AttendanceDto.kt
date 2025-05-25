package main.kotlin.com.smartattendance.dto

import main.kotlin.com.smartattendance.entity.Attendance

data class AttendanceResponse(
    val id: Long,
    val courseId: Long,
    val componentType: String, // e.g., "COURSE", "TUTORIAL", "LAB"
    val date: String,
    val status: String // e.g., "PRESENT", "ABSENT"
) {
    companion object {
        fun fromEntity(attendance: Attendance) = AttendanceResponse(
            id = attendance.id,
            courseId = attendance.course.id,
            componentType = attendance.componentType.name,
            date = attendance.date.toString(),
            status = attendance.status.name
        )
    }
}