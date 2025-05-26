package com.smartattendance.repository

import com.smartattendance.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Long> {
    fun existsByCourseName(courseName: String): Boolean
    fun existsByCourseCode(courseCode: String): Boolean
    fun findByStaffId(staffId: Long): List<Course>
}