package main.kotlin.com.smartattendance.controller

import main.kotlin.com.smartattendance.dto.CourseRequest
import main.kotlin.com.smartattendance.dto.CourseResponse
import com.smartattendance.dto.StudentResponse
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

    @PostMapping("/{courseId}/enroll")
    fun enrollStudent(
        @PathVariable courseId: Long,
        @RequestParam studentId: Long
    ): ResponseEntity<String> {
        courseService.enrollStudent(courseId, studentId)
        return ResponseEntity.ok("Student enrolled successfully")
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

    @GetMapping("/courses/{courseId}/students")
    fun getStudentsByCourseId(@PathVariable courseId: Long): ResponseEntity<List<StudentResponse>> {
        val students = courseService.getStudentsByCourseId(courseId)
        return ResponseEntity.ok(students)
    }
}