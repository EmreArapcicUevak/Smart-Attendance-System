package com.example.smartattendancesystemandroid.data.repository

import android.util.Log
import com.example.smartattendancesystemandroid.data.api.DataApi
import com.example.smartattendancesystemandroid.data.model.AttendanceRequest
import com.example.smartattendancesystemandroid.data.model.AttendanceResponse
import com.example.smartattendancesystemandroid.data.model.CourseListResponse
import com.example.smartattendancesystemandroid.data.model.CourseRequest
import com.example.smartattendancesystemandroid.data.model.CourseResponse
import com.example.smartattendancesystemandroid.data.model.StudentListResponse
import com.example.smartattendancesystemandroid.data.model.WeekAttendedState
import com.example.smartattendancesystemandroid.data.model.getWeekAttendedStateExamples
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

    override suspend fun getCourseStudents(courseId: Long): StudentListResponse {
        return try {
            api.getCourseStudents(courseId)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
            StudentListResponse(students = listOf())  //returns empty list
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
            StudentListResponse(students = listOf())  //returns empty list
        }
    }

    override suspend fun getStudentAttendance(
        courseId: Long,
        studentId: Long
    ): AttendanceResponse {
        return try {
            api.getStudentAttendance(courseId, studentId)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
            AttendanceResponse(lecture = getWeekAttendedStateExamples(WeekAttendedState.MISSED))
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
            AttendanceResponse(lecture = getWeekAttendedStateExamples(WeekAttendedState.MISSED))
        }
    }

    override suspend fun markAttendance(body: AttendanceRequest) {
        try {
            api.markAttendance(body)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
        }
    }

    override suspend fun getStudentCourses(): CourseListResponse {
        return try {
            api.getStudentCourses()
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
            CourseListResponse(courses = listOf(CourseResponse(1, "", "", "", false, false)))
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
            CourseListResponse(courses = listOf(CourseResponse(1, "", "", "", false, false)))
        }
    }

    override suspend fun createCourse(body: CourseRequest): CourseResponse {
        return try {
            api.createCourse(body)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
            CourseResponse(-1, "", "", "", false, false)
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
            CourseResponse(-1, "", "", "", false, false)
        }
    }

    override suspend fun updateCourse(body: CourseResponse): CourseResponse {
        return try {
            api.updateCourse(body)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
            CourseResponse(-1, "", "", "", false, false)
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
            CourseResponse(-1, "", "", "", false, false)
        }
    }

    override suspend fun deleteCourse(courseId: Long) {
        try {
            api.deleteCourse(courseId)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
        }
    }

    override suspend fun enrollStudent(courseId: Long, studentId: Long) {
        try {
            api.enrollStudent(courseId = courseId, studentId = studentId)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
        }
    }

    override suspend fun withdrawStudent(courseId: Long, studentId: Long) {
        try {
            api.withdrawStudent(courseId = courseId, studentId = studentId)
        }
        catch (e: HttpException) {
            Log.e("APIE", e.toString())
        }
        catch (e: Exception) {
            Log.e("APIE", e.toString())
        }
    }
}