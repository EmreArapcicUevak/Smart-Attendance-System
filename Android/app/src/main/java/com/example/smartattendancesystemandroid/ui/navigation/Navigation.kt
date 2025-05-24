package com.example.smartattendancesystemandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.smartattendancesystemandroid.data.model.UserType
import com.example.smartattendancesystemandroid.ui.screens.checkstudentcourseattendancescreen.CheckStudentCourseAttendanceScreen
import com.example.smartattendancesystemandroid.ui.screens.checkstudentcourseattendancescreen.CheckStudentCourseAttendanceScreenViewModel
import com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen.CourseDetailsScreen
import com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen.CourseDetailsScreenViewModel
import com.example.smartattendancesystemandroid.ui.screens.login.LoginScreen
import com.example.smartattendancesystemandroid.ui.screens.markattendancescreen.MarkAttendanceScreen
import com.example.smartattendancesystemandroid.ui.screens.staffhomescreen.StaffHomeScreen
import com.example.smartattendancesystemandroid.ui.screens.studenthomescreen.StudentHomeScreen
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
            LoginScreen(navigateToHomePage = {
                val page = when(it) {
                    UserType.STUDENT -> StudentHomeScreen
                    UserType.TEACHER -> StaffHomeScreen
                    UserType.ADMIN -> throw Error("User type is admin")
                }

                navController.navigate(page) {
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
                    navController.navigate(CourseDetailsScreen(it))
                },
                addCourseBtnPressed = {
                    //TODO
                }
            )
        }
        composable<CourseDetailsScreen> {
            val args = it.toRoute<CourseDetailsScreen>()
            val courseDetailsScreenViewModel = hiltViewModel<CourseDetailsScreenViewModel>()
            courseDetailsScreenViewModel.setup(args.courseId)
            CourseDetailsScreen(
                courseDetailsScreenViewModel = courseDetailsScreenViewModel,
                logoutPressed = {
                    navigationViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(CourseDetailsScreen) {
                            inclusive = true
                        }
                    }
                },
                navigateBackPressed = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                canNavigateBack = navController.previousBackStackEntry != null,
                cardPressed = { studentId, studentName, courseId ->
                    navController.navigate(CheckStudentCourseAttendanceScreen(
                        studentId = studentId,
                        studentName = studentName,
                        courseId = courseId
                    ))
                }
            )
        }
        composable<CheckStudentCourseAttendanceScreen> {
            val args = it.toRoute<CheckStudentCourseAttendanceScreen>()
            val checkStudentCourseAttendanceScreenViewModel: CheckStudentCourseAttendanceScreenViewModel = hiltViewModel<CheckStudentCourseAttendanceScreenViewModel>()
            checkStudentCourseAttendanceScreenViewModel.setup(
                studentId = args.studentId,
                studentName = args.studentName,
                courseId = args.courseId
            )
            CheckStudentCourseAttendanceScreen(
                checkStudentCourseAttendanceScreenViewModel = checkStudentCourseAttendanceScreenViewModel,
                logoutPressed = {
                    navigationViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(CheckStudentCourseAttendanceScreen) {
                            inclusive = true
                        }
                    }
                },
                navigateBackPressed = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                canNavigateBack = navController.previousBackStackEntry != null,
            )
        }
        composable<StudentHomeScreen> {
            StudentHomeScreen(
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
                cardPressed = {
                    //TODO
                }
            )
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
data class CheckStudentCourseAttendanceScreen(
    val studentId: Long,
    val studentName: String,
    val courseId: Long
)

@Serializable
object StudentHomeScreen

@Serializable
object MarkAttendanceScreen