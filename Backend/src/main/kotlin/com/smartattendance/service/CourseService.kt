package com.smartattendance.service

import com.smartattendance.dto.StudentResponse
import com.smartattendance.dto.CourseRequest
import com.smartattendance.dto.CourseResponse
import com.smartattendance.entity.Course
import com.smartattendance.repository.CourseRepository
import com.smartattendance.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository,
) {
    private val logger: Logger = LoggerFactory.getLogger(CourseService::class.java)

    fun createCourse(request: CourseRequest): CourseResponse {
        if (request.courseName.isBlank() || request.courseCode.isBlank()) {
            throw IllegalArgumentException("Course name and code are required")
        }

        val authentication = SecurityContextHolder.getContext().authentication
        val token = authentication.credentials as? String
            ?: throw IllegalArgumentException("User not authenticated")

        logger.debug("Extracted Token: $token") // Log the token for debugging

        val fullName = try {
            TokenService.extractFullName(token)
        } catch (e: Exception) {
            logger.error("Error extracting fullName: ${e.message}")
            throw IllegalArgumentException("Unable to extract full name from token")
        }

        logger.info("Extracted Full Name: $fullName") // Log the extracted full name

        val course = Course(
            courseName = request.courseName,
            courseCode = request.courseCode,
            createdBy = fullName,
            staffId = TokenService.extractId(token),
            hasLabs = request.hasLabs,
            hasTutorials = request.hasTutorials,
        )
        val saved = courseRepository.save(course)
        return CourseResponse(
            id = saved.id,
            courseName = saved.courseName,
            courseCode = saved.courseCode,
        )
    }

    fun updateCourse(id: Long, request: CourseRequest): CourseResponse {
        val course = courseRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Course not found") }
        course.courseName = request.courseName
        course.courseCode = request.courseCode
        courseRepository.save(course)
        return CourseResponse(
            id = course.id,
            courseName = course.courseName,
            courseCode = course.courseCode,
        )
    }

    fun enrollStudent(courseId: Long, studentId: Long) {
        val course = courseRepository.findById(courseId)
            .orElseThrow { IllegalArgumentException("Course not found") }

        val student = userRepository.findByStudentId(studentId)
            ?: throw IllegalArgumentException("Student not found")
        course.students.add(student)
        courseRepository.save(course)
    }

    fun getAllCourses(): List<CourseResponse> {
        return courseRepository.findAll().map { course ->
            CourseResponse(
                id = course.id,
                courseName = course.courseName,
                courseCode = course.courseCode,
            )
        }
    }

    fun getCourseById(id: Long): CourseResponse {
        val course = courseRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Course not found") }
        return CourseResponse(
            id = course.id,
            courseName = course.courseName,
            courseCode = course.courseCode,
        )
    }

    fun getStudentsByCourseId(courseId: Long): List<StudentResponse> {
        val course = courseRepository.findById(courseId)
            .orElseThrow { IllegalArgumentException("Course not found") }

        return course.students.map { student ->
            StudentResponse(
                studentId = student.studentId,
                fullName = student.fullName,
            )
        }
    }

    fun getCoursesByStaffId(): List<CourseResponse> {

        val authentication = SecurityContextHolder.getContext().authentication
        val token = authentication.credentials as? String
            ?: throw IllegalArgumentException("User not authenticated")

        val staffId = try {
            TokenService.extractId(token)
        } catch (e: Exception) {
            throw IllegalArgumentException("Unable to extract staffId from token")
        }

        val courses = courseRepository.findByStaffId(staffId)

        return courses.map { course ->
            CourseResponse(
                id = course.id,
                courseName = course.courseName,
                courseCode = course.courseCode,
            )
        }
    }

    fun getCoursesByStudentId(id: Long): List<CourseResponse> {
        val student = userRepository.findById(id).orElseThrow { IllegalArgumentException("Student not found") }
        val courses = courseRepository.findByStudentsContaining(student)

        return courses.map { course ->
            CourseResponse(
                id = course.id,
                courseName = course.courseName,
                courseCode = course.courseCode,
            )
        }
    }
}