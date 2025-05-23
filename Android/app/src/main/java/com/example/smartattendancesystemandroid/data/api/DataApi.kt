package com.example.smartattendancesystemandroid.data.api

import com.example.smartattendancesystemandroid.data.model.AttendanceRequest
import com.example.smartattendancesystemandroid.data.model.AttendanceResponse
import com.example.smartattendancesystemandroid.data.model.CourseRequest
import com.example.smartattendancesystemandroid.data.model.CourseResponse
import com.example.smartattendancesystemandroid.data.model.Student
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DataApi {

    @POST("course/{courseId}/enroll")
    suspend fun enrollStudent(
        @Path("courseId") courseId: Long,
        @Query("studentId") studentId: Long
    ): Response<Unit>

    @GET("course/{courseId}/students")
    suspend fun getCourseStudents(
        @Path("courseId") courseId: Long
    ): List<Student>

    @GET("course/staff-courses")
    suspend fun getStaffCourses(): List<CourseResponse>

    @GET("course/student-courses")
    suspend fun getStudentCourses(): List<CourseResponse>

    @POST("course")
    suspend fun createCourse(
        @Body body: CourseRequest
    ): CourseResponse

    @PUT("course")
    suspend fun updateCourse(
        @Body body: CourseResponse
    ): CourseResponse

    @POST("attendance/mark")
    suspend fun markAttendance(
        @Body body: AttendanceRequest
    ): Response<Unit>

    @GET("attendance/{studentId}")
    suspend fun getStudentAttendance(
        @Path("studentId") studentId: Long
    ): AttendanceResponse
}