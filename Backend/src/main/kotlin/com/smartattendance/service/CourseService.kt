package main.kotlin.com.smartattendance.service

import main.kotlin.com.smartattendance.dto.CourseRequest
import main.kotlin.com.smartattendance.dto.CourseResponse
import main.kotlin.com.smartattendance.entity.Course
import main.kotlin.com.smartattendance.repository.CourseRepository
import main.kotlin.com.smartattendance.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class CourseService(
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    open fun createCourse(request: CourseRequest): CourseResponse {
        if (request.courseName.isBlank() || request.courseCode.isBlank()) {
            throw IllegalArgumentException("Course name and code are required")
        }

        val course = Course(
            courseName = request.courseName,
            courseCode = request.courseCode,
        )
        val saved = courseRepository.save(course)
        return CourseResponse(id = saved.id)
    }
}