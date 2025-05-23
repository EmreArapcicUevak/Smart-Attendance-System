package com.smartattendance.service

import com.smartattendance.dto.AttendanceRequest
import com.smartattendance.dto.AttendanceResponse
import com.smartattendance.entity.Attendance
import com.smartattendance.repository.AttendanceRepository
import com.smartattendance.repository.UserRepository
import com.smartattendance.repository.CourseRepository
import com.smartattendance.entity.AttendanceStatus
import org.springframework.stereotype.Service

@Service
class AttendanceService(
    private val attendanceRepository: AttendanceRepository,
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository,
    private val courseService: CourseService,
) {
    fun getAttendanceForStudent(studentId: Long, courseId: Long?): List<AttendanceResponse> {
        val records = if (courseId != null) {
            attendanceRepository.findByStudentIdAndCourseId(studentId, courseId)
        } else {
            attendanceRepository.findByStudentId(studentId)
        }
        return records.map { AttendanceResponse.fromEntity(it) }
    }

    fun markAttendance(courseId: Long, attendanceRequest: AttendanceRequest) {
        val students = courseService.getStudentsByCourseId(courseId)
            .filter { it.studentId in attendanceRequest.studentIds }

        if (students.isEmpty()) {
            throw IllegalArgumentException("No valid students found for the given course and IDs")
        }

        val attendanceRecords = students.map { student ->
            val existingRecord = attendanceRepository.findByStudentIdAndCourseIdAndComponentTypeAndWeekNumber(
                student.studentId, courseId, attendanceRequest.componentType, attendanceRequest.weekNumber
            )

            if (existingRecord != null) {
                throw IllegalArgumentException("Duplicate attendance record for student ${student.studentId}")
            }

            Attendance(
                student = userRepository.findByStudentId(student.studentId)
                    ?: throw IllegalArgumentException("Student not found"),
                studentId = student.studentId,
                course = courseRepository.findById(courseId)
                    .orElseThrow { IllegalArgumentException("Course not found") },
                componentType = attendanceRequest.componentType,
                weekNumber = attendanceRequest.weekNumber,
                status = AttendanceStatus.PRESENT
            )
        }

        attendanceRepository.saveAll(attendanceRecords)
    }
}