package com.smartattendance.controller

import com.smartattendance.dto.AttendanceResponse
import com.smartattendance.dto.AttendanceRequest
import com.smartattendance.service.AttendanceService
import org.springframework.http.ResponseEntity
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

    @PostMapping("/courses/{courseId}/attendance")
    fun markAttendance(
        @PathVariable courseId: Long,
        @RequestBody attendanceRequest: AttendanceRequest
    ): ResponseEntity<Void> {
        attendanceService.markAttendance(courseId, attendanceRequest)
        return ResponseEntity.ok().build()
    }
}