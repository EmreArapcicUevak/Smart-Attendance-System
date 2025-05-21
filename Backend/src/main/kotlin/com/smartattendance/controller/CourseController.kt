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

    @PutMapping("/{id}")
    fun updateCourse(
        @PathVariable id: Long,
        @RequestBody request: CourseRequest
    ): ResponseEntity<CourseResponse> =
        try {
            ResponseEntity.ok(courseService.updateCourse(id, request))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<CourseResponse> =
        try {
            ResponseEntity.ok(courseService.getCourseById(id))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
}