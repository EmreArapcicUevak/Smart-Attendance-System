package com.smartattendance.service

import com.smartattendance.dto.AttendanceRequest
import com.smartattendance.dto.AttendanceResponse
import com.smartattendance.dto.AttendanceStatusResponse
import com.smartattendance.entity.Attendance
import com.smartattendance.entity.ComponentType
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
    fun getAttendanceStatuses(id: Long, courseId: Long): List<String> {
        return attendanceRepository.findAllByStudentIdAndCourseId(id, courseId)
            .sortedBy { it.weekNumber }
            .map { it.status.name }
    }

    fun markAttendance(courseId: Long, attendanceRequest: AttendanceRequest) {
        val course = courseRepository.findById(courseId)
            .orElseThrow { IllegalArgumentException("Course not found") }

        // Validate component type
        when (attendanceRequest.componentType) {
            ComponentType.LAB -> {
                if (!course.hasLabs) {
                    throw IllegalArgumentException("The course does not have labs")
                }
            }

            ComponentType.TUTORIAL -> {
                if (!course.hasTutorials) {
                    throw IllegalArgumentException("The course does not have tutorials")
                }
            }

            ComponentType.LECTURE -> {
                // Assuming all courses have lectures by default
            }

            else -> throw IllegalArgumentException("Invalid component type")
        }

        val enrolledStudentIds = course.students.map { it.studentId }.toSet()
        val presentStudentIds = attendanceRequest.studentIds.toSet()

        presentStudentIds.forEach { studentId ->
            if (studentId !in enrolledStudentIds) {
                throw IllegalArgumentException("Student with ID $studentId is not enrolled in the course")
            }
        }

        val attendanceRecords = course.students.map { student ->
            val existingRecord = attendanceRepository.findByStudentIdAndCourseIdAndComponentTypeAndWeekNumber(
                student.studentId, courseId, attendanceRequest.componentType, attendanceRequest.weekNumber
            )

            if (existingRecord != null) {
                throw IllegalArgumentException("Duplicate attendance record for student ${student.studentId}")
            }

            Attendance(
                student = student,
                studentId = student.studentId,
                course = course,
                componentType = attendanceRequest.componentType,
                weekNumber = attendanceRequest.weekNumber,
                status = if (student.studentId in presentStudentIds) AttendanceStatus.PRESENT else AttendanceStatus.ABSENT
            )
        }

        attendanceRepository.saveAll(attendanceRecords)
    }

    fun getAttendanceStatusesByComponentType(id: Long, courseId: Long): List<AttendanceResponse> {

        val student = userRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Student not found") }
        val course = courseRepository.findById(courseId)
            .orElseThrow { IllegalArgumentException("Course not found") }

        if (!course.students.contains(student)) {
            throw IllegalArgumentException("Student is not enrolled in the course")
        }

        val studentId = student.studentId

        val records = attendanceRepository.findAllByStudentIdAndCourseId(studentId, courseId)
            .sortedBy { it.weekNumber }

        return records.map { AttendanceResponse.fromEntity(it) }
    }

    fun getAttendanceByCourseId(courseId: Long): List<AttendanceStatusResponse> {
        val attendances = attendanceRepository.findAllByCourseId(courseId)
        return attendances.map { AttendanceStatusResponse.fromEntity(it) }
    }
}