package main.kotlin.com.smartattendance.service

import main.kotlin.com.smartattendance.dto.CourseRequest
import main.kotlin.com.smartattendance.dto.CourseResponse
import main.kotlin.com.smartattendance.entity.Course
import main.kotlin.com.smartattendance.repository.CourseRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class CourseService(
    private val courseRepository: CourseRepository,
) {
    private val logger: Logger = LoggerFactory.getLogger(CourseService::class.java)
    @Transactional
    open fun createCourse(request: CourseRequest): CourseResponse {
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
            createdBy = fullName
        )
        val saved = courseRepository.save(course)
        return CourseResponse(id = saved.id)
    }
}