package com.smartattendance.service

import com.smartattendance.dto.AttendanceRequest
import com.smartattendance.dto.AttendanceResponse
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
    fun getAttendanceStatuses(studentId: Long, courseId: Long): List<String> {
        return attendanceRepository.findByStudentIdAndCourseId(studentId, courseId)
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

        val allStudents = courseService.getStudentsByCourseId(courseId)
        val presentStudentIds = attendanceRequest.studentIds.toSet()

        val attendanceRecords = allStudents.map { student ->
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
                course = course,
                componentType = attendanceRequest.componentType,
                weekNumber = attendanceRequest.weekNumber,
                status = if (student.studentId in presentStudentIds) AttendanceStatus.PRESENT else AttendanceStatus.ABSENT
            )
        }

        attendanceRepository.saveAll(attendanceRecords)
    }

    fun getAttendanceStatusesByComponentType(studentId: Long, courseId: Long): Map<String, List<String>> {
        val records = attendanceRepository.findByStudentIdAndCourseId(studentId, courseId)
            .sortedBy { it.weekNumber }

        return records.groupBy { it.componentType.name }
            .mapValues { (_, attendanceList) -> attendanceList.map { it.status.name } }
    }
}