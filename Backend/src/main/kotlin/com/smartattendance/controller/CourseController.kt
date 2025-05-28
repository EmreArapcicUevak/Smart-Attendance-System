package com.smartattendance.controller

import com.smartattendance.dto.CourseRequest
import com.smartattendance.dto.CourseResponse
import com.smartattendance.dto.StudentResponse
import com.smartattendance.service.CourseService
import org.springframework.http.ResponseEntity
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

    @GetMapping
    fun getAllCourses(): ResponseEntity<List<CourseResponse>> {
        val courses = courseService.getAllCourses()
        return ResponseEntity.ok(courses)
    }

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<CourseResponse> =
        try {
            ResponseEntity.ok(courseService.getCourseById(id))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }

    @GetMapping("/{courseId}/students")
    fun getStudentsByCourseId(@PathVariable courseId: Long): ResponseEntity<List<StudentResponse>> {
        val students = courseService.getStudentsByCourseId(courseId)
        return ResponseEntity.ok(students)
    }

    @GetMapping("/staff/{staffId}")
    fun getCoursesByStaff(@PathVariable staffId: Long): ResponseEntity<List<CourseResponse>> {
        val courses = courseService.getCoursesByStaffId(staffId)
        return ResponseEntity.ok(courses)
    }

    @GetMapping("/student/{studentId}")
    fun getCoursesByStudentId(@PathVariable studentId: Long): ResponseEntity<List<CourseResponse>> {
        val courses = courseService.getCoursesByStudentId(studentId)
        return ResponseEntity.ok(courses)
    }

    @DeleteMapping("/{id}")
    fun deleteCourseById(@PathVariable id: Long): ResponseEntity<Void> =
        courseService.deleteCourseById(id)

}