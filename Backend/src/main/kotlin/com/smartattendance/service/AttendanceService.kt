package main.kotlin.com.smartattendance.service

import main.kotlin.com.smartattendance.dto.AttendanceResponse
import main.kotlin.com.smartattendance.repository.AttendanceRepository
import org.springframework.stereotype.Service

@Service
class AttendanceService(
    private val attendanceRepository: AttendanceRepository
) {
    fun getAttendanceForStudent(studentId: Long, courseId: Long?): List<AttendanceResponse> {
        val records = if (courseId != null) {
            attendanceRepository.findByStudentIdAndCourseId(studentId, courseId)
        } else {
            attendanceRepository.findByStudentId(studentId)
        }
        return records.map { AttendanceResponse.fromEntity(it) }
    }
}