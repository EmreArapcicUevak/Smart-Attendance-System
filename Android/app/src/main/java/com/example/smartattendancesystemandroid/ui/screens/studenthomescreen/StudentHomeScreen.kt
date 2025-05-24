package com.example.smartattendancesystemandroid.ui.screens.studenthomescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import com.example.smartattendancesystemandroid.ui.components.LoadingCircleScreen
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun StudentHomeScreen(
    logoutPressed: () -> Unit,
    navigateBackPressed: () -> Unit,
    canNavigateBack: Boolean,
    cardPressed: (Long, Long, String) -> Unit,
    studentHomeScreenViewModel: StudentHomeScreenViewModel = hiltViewModel<StudentHomeScreenViewModel>()
) {
    val studentHomeScreenUiState by studentHomeScreenViewModel.uiState.collectAsState()

    if (studentHomeScreenUiState.isLoading) {
        LoadingCircleScreen()
        return
    }

    StudentHomeScreenContent(
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack,
        studentCourses = studentHomeScreenUiState.studentCourses,
        cardPressed = {courseId, courseName -> cardPressed(studentHomeScreenViewModel.getStudentId(), courseId, courseName)}
    )
}

@Composable
private fun StudentHomeScreenContent(
    topAppBarTitle: String = "Dashboard",
    logoutPressed: () -> Unit = {},
    navigateBackPressed: () -> Unit = {},
    canNavigateBack: Boolean = false,
    studentCourses: List<StudentCourseCardData> = listOf(),
    cardPressed: (Long, String) -> Unit = {ci, cn ->},
) {
    Skeleton(
        topAppBarTitle = topAppBarTitle,
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack
    ) {
        StudentCourseList(
            studentCourses = studentCourses,
            cardPressed = {courseId, courseName -> cardPressed(courseId, courseName)},
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StudentHomeScreenContentPreview() {

    val courses = listOf(
        StudentCourseCardData(
            id = 1,
            courseName = "Introduction to Computer Science",
            courseCode = "CS101",
            courseFaculty = "FENS"
        ),
        StudentCourseCardData(
            id = 2,
            courseName = "Psychology 101",
            courseCode = "PSY101",
            courseFaculty = "FASS"
        ),
        StudentCourseCardData(
            id = 3,
            courseName = "Business Management",
            courseCode = "BUS201",
            courseFaculty = "FAP"
        ),
        StudentCourseCardData(
            id = 4,
            courseName = "Academic Writing",
            courseCode = "ENG102",
            courseFaculty = "ELS"
        ),
        StudentCourseCardData(
            id = 5,
            courseName = "Data Structures",
            courseCode = "CS202",
            courseFaculty = "FENS"
        )
    )


    SmartAttendanceSystemAndroidTheme {
        StudentHomeScreenContent(
            studentCourses = courses
        )
    }
}