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
import com.example.smartattendancesystemandroid.ui.screens.createcoursescreen.CreateCourseScreen
import com.example.smartattendancesystemandroid.ui.screens.editcoursescreen.EditCourseScreen
import com.example.smartattendancesystemandroid.ui.screens.editcoursescreen.EditCourseScreenViewModel
import com.example.smartattendancesystemandroid.ui.screens.login.LoginScreen
import com.example.smartattendancesystemandroid.ui.screens.markattendancescreen.MarkAttendanceScreen
import com.example.smartattendancesystemandroid.ui.screens.markattendancescreen.MarkAttendanceScreenViewModel
import com.example.smartattendancesystemandroid.ui.screens.staffhomescreen.StaffHomeScreen
import com.example.smartattendancesystemandroid.ui.screens.studentcoursedetailsscreen.StudentCourseDetailsScreenViewModel
import com.example.smartattendancesystemandroid.ui.screens.studenthomescreen.StudentHomeScreen
import com.example.smartattendancesystemandroid.ui.screens.studentcoursedetailsscreen.StudentCourseDetailsScreen
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
                    navController.navigate(EditCourseScreen(it))
                },
                courseCardPressed = {
                    navController.navigate(CourseDetailsScreen(it))
                },
                addCourseBtnPressed = {
                    navController.navigate(CreateCourseScreen)
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
                },
                takeAttendancePressed = {
                    navController.navigate(MarkAttendanceScreen(courseId = it))
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
                        popUpTo(StudentHomeScreen) {
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
                cardPressed = { studentId, courseId, courseName ->
                    navController.navigate(StudentCourseDetailsScreen(
                        studentId = studentId,
                        courseId = courseId,
                        courseName = courseName
                    ))
                }
            )
        }
        composable<StudentCourseDetailsScreen> {
            val args = it.toRoute<StudentCourseDetailsScreen>()
            val studentCourseDetailsScreenViewModel: StudentCourseDetailsScreenViewModel = hiltViewModel<StudentCourseDetailsScreenViewModel>()
            studentCourseDetailsScreenViewModel.setup(
                studentId = args.studentId,
                courseId = args.courseId,
                courseName = args.courseName
            )
            StudentCourseDetailsScreen(
                studentCourseDetailsScreenViewModel = studentCourseDetailsScreenViewModel,
                logoutPressed = {
                    navigationViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(StudentCourseDetailsScreen) {
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
                )
        }
        composable<MarkAttendanceScreen> {
            val args = it.toRoute<MarkAttendanceScreen>()
            val markAttendanceScreenViewModel = hiltViewModel<MarkAttendanceScreenViewModel>()
            markAttendanceScreenViewModel.setup(args.courseId)
            MarkAttendanceScreen(
                markAttendanceScreenViewModel = markAttendanceScreenViewModel,
                logoutPressed = {
                    navigationViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(MarkAttendanceScreen) {
                            inclusive = true
                        }
                    }
                },
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateBackPressed = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            )
        }
        composable<CreateCourseScreen> {
            CreateCourseScreen(
                logoutPressed = {
                    navigationViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(CreateCourseScreen) {
                            inclusive = true
                        }
                    }
                },
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateBackPressed = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            )
        }
        composable<EditCourseScreen> {
            val args = it.toRoute<EditCourseScreen>()
            val editCourseScreenViewModel = hiltViewModel<EditCourseScreenViewModel>(it)
            editCourseScreenViewModel.setup(args.courseId)
            EditCourseScreen(
                editCourseScreenViewModel = editCourseScreenViewModel,
                logoutPressed = {
                    navigationViewModel.logout()
                    navController.navigate(LoginScreen) {
                        popUpTo(EditCourseScreen) {
                            inclusive = true
                        }
                    }
                },
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateBackPressed = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            )
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
object CreateCourseScreen

@Serializable
data class EditCourseScreen(
    val courseId: Long
)

@Serializable
data class CheckStudentCourseAttendanceScreen(
    val studentId: Long,
    val studentName: String,
    val courseId: Long
)

@Serializable
object StudentHomeScreen

@Serializable
data class StudentCourseDetailsScreen(
    val studentId: Long,
    val courseId: Long,
    val courseName: String
)

@Serializable
data class MarkAttendanceScreen (
    val courseId: Long
)