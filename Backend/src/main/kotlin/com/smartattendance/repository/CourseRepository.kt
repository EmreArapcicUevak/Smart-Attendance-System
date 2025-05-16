package main.kotlin.com.smartattendance.repository

import main.kotlin.com.smartattendance.entity.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long> {
    fun existsByCourseName(courseName: String): Boolean
    fun existsByCourseCode(courseCode: String): Boolean
}