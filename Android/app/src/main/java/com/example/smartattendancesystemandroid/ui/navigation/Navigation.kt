package com.example.smartattendancesystemandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
    val navigationViewModel: NavigationViewModel = hiltViewModel<NavigationViewModel>()

    NavHost(
        navController = navController,
        startDestination = LoginScreen
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
            StaffHomeScreen(
                logoutPressed = {
                    navigationViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(StaffHomeScreen) {
                            inclusive = true
                        }
                    }
                },
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateBackPressed = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                courseSettingsPressed = {
                    navController.navigate(CourseSettingsScreen(it))
                },
                courseCardPressed = {
                    navController.navigate(CourseSettingsScreen(it))
                },
                addCourseBtnPressed = {

                }
            )
        }
        composable<CourseDetailsScreen> {
            CourseDetailsScreen(
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
data class CourseDetailsScreen(val courseId: Long)

@Serializable
data class CourseSettingsScreen(val courseId: Long)

@Serializable
object CreateCourseScreen

@Serializable
object CheckStudentCourseAttendanceScreen

@Serializable
object MarkAttendanceScreen