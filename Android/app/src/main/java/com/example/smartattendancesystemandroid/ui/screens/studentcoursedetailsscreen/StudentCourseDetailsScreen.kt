package com.example.smartattendancesystemandroid.ui.screens.studentcoursedetailsscreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import com.example.smartattendancesystemandroid.data.model.WeekAttendedState
import com.example.smartattendancesystemandroid.data.model.getWeekAttendedStateExamples
import com.example.smartattendancesystemandroid.ui.components.CourseComponentAttendanceCard
import com.example.smartattendancesystemandroid.ui.components.LoadingCircleScreen
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun StudentCourseDetailsScreen(
    logoutPressed: () -> Unit,
    navigateBackPressed: () -> Unit,
    canNavigateBack: Boolean,
    studentCourseDetailsScreenViewModel: StudentCourseDetailsScreenViewModel = hiltViewModel<StudentCourseDetailsScreenViewModel>()
) {

    val studentCourseDetailsScreenUiState by studentCourseDetailsScreenViewModel.uiState.collectAsState()

    if (studentCourseDetailsScreenUiState.isLoading) {
        LoadingCircleScreen()
        return
    }

    StudentCourseDetailsScreenContent(
        topAppBarTitle = "${studentCourseDetailsScreenUiState.courseName} Details",
        logoutPressed = logoutPressed,
        navigateBackPressedPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack,
        lectureWeekAttendedStateList = studentCourseDetailsScreenUiState.lectureWeekAttendedStateList,
        tutorialWeekAttendedStateList = studentCourseDetailsScreenUiState.tutorialWeekAttendedStateList,
        labWeekAttendedStateList = studentCourseDetailsScreenUiState.labWeekAttendedStateList
    )
}

@Composable
private fun StudentCourseDetailsScreenContent(
    topAppBarTitle: String = "CS302 Details",
    logoutPressed: () -> Unit = {},
    navigateBackPressedPressed: () -> Unit = {},
    canNavigateBack: Boolean = true,
    lectureWeekAttendedStateList: List<WeekAttendedState>,
    tutorialWeekAttendedStateList: List<WeekAttendedState>? = null,
    labWeekAttendedStateList: List<WeekAttendedState>? = null,
) {
    Skeleton(
        topAppBarTitle = topAppBarTitle,
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressedPressed,
        canNavigateBack = canNavigateBack,
    ) {

        LazyColumn {
            items(1) {
                CourseComponentAttendanceCard(
                    componentName = "Lecture",
                    weekAttendedStateList = lectureWeekAttendedStateList,
                    onClick = {i,s ->},
                )

                if (tutorialWeekAttendedStateList != null) {
                    CourseComponentAttendanceCard(
                        componentName = "Tutorial",
                        weekAttendedStateList = tutorialWeekAttendedStateList,
                        onClick = {i,s ->},
                    )
                }

                if (labWeekAttendedStateList != null) {
                    CourseComponentAttendanceCard(
                        componentName = "Lab",
                        weekAttendedStateList = labWeekAttendedStateList,
                        onClick = {i,s ->},
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StudentCourseDetailsScreenContentPreview() {
    SmartAttendanceSystemAndroidTheme {
        StudentCourseDetailsScreenContent(
            lectureWeekAttendedStateList = getWeekAttendedStateExamples(),
            tutorialWeekAttendedStateList = getWeekAttendedStateExamples(WeekAttendedState.NOT_MARKED),
            labWeekAttendedStateList = getWeekAttendedStateExamples(WeekAttendedState.NOT_MARKED)
        )
    }
}