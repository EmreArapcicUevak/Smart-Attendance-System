package main.kotlin.com.smartattendance.controller

import main.kotlin.com.smartattendance.dto.CourseRequest
import main.kotlin.com.smartattendance.dto.CourseResponse
import main.kotlin.com.smartattendance.service.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
class CourseController(
    private val courseService: CourseService
) {
    @PostMapping
    fun createCourse(@RequestBody request: CourseRequest): ResponseEntity<CourseResponse> =
        try {
            ResponseEntity.ok(courseService.createCourse(request))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
}