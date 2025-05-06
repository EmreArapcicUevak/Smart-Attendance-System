package com.example.smartattendancesystemandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartattendancesystemandroid.ui.screens.checkstudentcourseattendancescreen.CheckStudentCourseAttendanceScreen
import com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen.CourseDetailsScreen
import com.example.smartattendancesystemandroid.ui.screens.login.LoginScreen
import com.example.smartattendancesystemandroid.ui.screens.markattendancescreen.MarkAttendanceScreen
import com.example.smartattendancesystemandroid.ui.screens.staffhomescreen.StaffHomeScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MarkAttendanceScreen
    ) {
        composable<LoginScreen> {
            LoginScreen(navigateToStaffHomePage = {
                navController.navigate(StaffHomeScreen) {
                    popUpTo(LoginScreen) {
                        inclusive = true
                    }
                }
            })
        }
        composable<StaffHomeScreen> {
            StaffHomeScreen()
        }
        composable<CourseDetailsScreen> {
            CourseDetailsScreen(
                settingsPressed = {},
                logoutPressed = {},
                navigateBackPressed = {},
                canNavigateBack = true,
                cardPressed = {}
            )
        }
        composable<CheckStudentCourseAttendanceScreen> {
            CheckStudentCourseAttendanceScreen()
        }
        composable<MarkAttendanceScreen> {
            MarkAttendanceScreen()
        }
    }
}

@Serializable
object LoginScreen

@Serializable
object StaffHomeScreen

@Serializable
object CourseDetailsScreen

@Serializable
object CheckStudentCourseAttendanceScreen

@Serializable
object MarkAttendanceScreen

/*
@Serializable
data class Example(
    val example: Int,
    val example2: String?
)
*/
