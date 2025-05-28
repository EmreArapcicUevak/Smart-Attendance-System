package com.smartattendance.repository

import com.smartattendance.entity.Attendance
import com.smartattendance.entity.ComponentType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttendanceRepository : JpaRepository<Attendance, Long> {
    fun findByStudentId(studentId: Long): List<Attendance>
    fun findAllByStudentIdAndCourseId(studentId: Long, courseId: Long): List<Attendance>
    fun findByStudentIdAndCourseIdAndComponentTypeAndWeekNumber(
        studentId: Long,
        courseId: Long,
        componentType: ComponentType,
        weekNumber: Int
    ): Attendance?
    fun findAllByCourseId(courseId : Long): List<Attendance>
}