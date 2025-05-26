package com.smartattendance.controller

import com.smartattendance.dto.AttendanceResponse
import com.smartattendance.dto.AttendanceRequest
import com.smartattendance.entity.ComponentType
import com.smartattendance.service.AttendanceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
class AttendanceController(
    private val attendanceService: AttendanceService
) {
    @GetMapping("/{studentId}/courses/{courseId}/attendance")
    fun getAttendanceStatusesByComponentType(
        @PathVariable studentId: Long,
        @PathVariable courseId: Long
    ): ResponseEntity<Map<String, List<String>>> {
        val statusesByComponent = attendanceService.getAttendanceStatusesByComponentType(studentId, courseId)
        return ResponseEntity.ok(statusesByComponent)
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