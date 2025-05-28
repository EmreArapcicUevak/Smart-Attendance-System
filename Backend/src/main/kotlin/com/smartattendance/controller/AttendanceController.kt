package com.smartattendance.controller

import org.springframework.security.core.context.SecurityContextHolder
import com.smartattendance.dto.AttendanceResponse
import com.smartattendance.dto.AttendanceRequest
import com.smartattendance.dto.AttendanceStatusResponse
import com.smartattendance.service.AttendanceService
import com.smartattendance.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
class AttendanceController(
    private val attendanceService: AttendanceService,
    private val userRepository: UserRepository
) {
    @GetMapping("/{id}/courses/{courseId}/attendance")
    fun getAttendanceStatusesByComponentType(
        @PathVariable id: Long,
        @PathVariable courseId: Long
    ): ResponseEntity<List<AttendanceResponse>> {
        val attendanceResponses = attendanceService.getAttendanceStatusesByComponentType(id, courseId)
        return ResponseEntity.ok(attendanceResponses)
    }

    @PostMapping("/courses/{courseId}/attendance")
    fun markAttendance(
        @PathVariable courseId: Long,
        @RequestBody attendanceRequest: AttendanceRequest
    ): ResponseEntity<Void> {
        attendanceService.markAttendance(courseId, attendanceRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{courseId}/attendance")
    fun getAttendanceByCourseId(
        @PathVariable courseId: Long
    ): ResponseEntity<List<AttendanceStatusResponse>> {

        val attendanceResponses = attendanceService.getAttendanceByCourseId(courseId)
        return ResponseEntity.ok(attendanceResponses)
    }
}