package main.kotlin.com.smartattendance.controller

import main.kotlin.com.smartattendance.dto.AttendanceResponse
import main.kotlin.com.smartattendance.service.AttendanceService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
class AttendanceController(
    private val attendanceService: AttendanceService
) {
    @GetMapping("/{studentId}/attendance")
    fun getStudentAttendance(
        @PathVariable studentId: Long,
        @RequestParam(required = false) courseId: Long?
    ): ResponseEntity<List<AttendanceResponse>> {
        val records = attendanceService.getAttendanceForStudent(studentId, courseId)
        return ResponseEntity.ok(records)
    }
}