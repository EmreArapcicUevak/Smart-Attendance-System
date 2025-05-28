package com.example.smartattendancesystemandroid.data.repository

import com.example.smartattendancesystemandroid.data.model.AttendanceRequest
import com.example.smartattendancesystemandroid.data.model.AttendanceResponse
import com.example.smartattendancesystemandroid.data.model.CourseListResponse
import com.example.smartattendancesystemandroid.data.model.CourseRequest
import com.example.smartattendancesystemandroid.data.model.CourseResponse
import com.example.smartattendancesystemandroid.data.model.StudentListResponse

interface DataRepository {
    suspend fun getStaffCourses(): CourseListResponse
    suspend fun getCourseStudents(courseId: Long): StudentListResponse
    suspend fun getStudentAttendance(courseId: Long, studentId: Long): AttendanceResponse
    suspend fun markAttendance(body: AttendanceRequest)
    suspend fun getStudentCourses(): CourseListResponse
    suspend fun createCourse(body: CourseRequest): CourseResponse
    suspend fun updateCourse(body: CourseResponse): CourseResponse
    suspend fun deleteCourse(courseId: Long)
    suspend fun enrollStudent(courseId: Long, studentId: Long)
    suspend fun withdrawStudent(courseId: Long, studentId: Long)
}