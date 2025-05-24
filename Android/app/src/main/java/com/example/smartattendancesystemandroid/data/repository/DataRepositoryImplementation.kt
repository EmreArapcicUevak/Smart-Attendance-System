package com.example.smartattendancesystemandroid.data.repository

import android.util.Log
import com.example.smartattendancesystemandroid.data.api.DataApi
import com.example.smartattendancesystemandroid.data.model.CourseListResponse
import retrofit2.HttpException

class DataRepositoryImplementation(
    private val api: DataApi
): DataRepository {
    override suspend fun getStaffCourses(): CourseListResponse {
        return try {
            api.getStaffCourses()
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
            CourseListResponse(courses = listOf())  //returns empty list
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
            CourseListResponse(courses = listOf())  //returns empty list
        }
    }
}