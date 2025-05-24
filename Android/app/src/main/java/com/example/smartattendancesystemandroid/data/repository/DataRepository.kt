package com.example.smartattendancesystemandroid.data.repository

import com.example.smartattendancesystemandroid.data.model.CourseListResponse
import com.example.smartattendancesystemandroid.data.model.CourseResponse
import com.example.smartattendancesystemandroid.data.model.StudentListResponse

interface DataRepository {
    suspend fun getStaffCourses(): CourseListResponse
    suspend fun getCourseStudents(courseId: Long): StudentListResponse
}