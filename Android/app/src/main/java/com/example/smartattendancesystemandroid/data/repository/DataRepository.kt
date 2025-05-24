package com.example.smartattendancesystemandroid.data.repository

import com.example.smartattendancesystemandroid.data.model.CourseListResponse
import com.example.smartattendancesystemandroid.data.model.CourseResponse

interface DataRepository {
    suspend fun getStaffCourses(): CourseListResponse
}