package main.kotlin.com.smartattendance.repository

import main.kotlin.com.smartattendance.entity.Attendance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttendanceRepository : JpaRepository<Attendance, Long> {
    fun findByStudentId(studentId: Long): List<Attendance>
    fun findByStudentIdAndCourseId(studentId: Long, courseId: Long): List<Attendance>
}