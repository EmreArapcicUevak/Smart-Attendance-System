package com.example.smartattendancesystemandroid.ui.screens.studentcoursedetailsscreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartattendancesystemandroid.data.model.WeekAttendedState
import com.example.smartattendancesystemandroid.data.model.getWeekAttendedStateExamples
import com.example.smartattendancesystemandroid.ui.components.CourseComponentAttendanceCard
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun StudentCourseDetailsScreen() {
    //StudentCourseDetailsScreenContent()
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
                    onClick = {},
                )

                if (tutorialWeekAttendedStateList != null) {
                    CourseComponentAttendanceCard(
                        componentName = "Tutorial",
                        weekAttendedStateList = tutorialWeekAttendedStateList,
                        onClick = {},
                    )
                }

                if (labWeekAttendedStateList != null) {
                    CourseComponentAttendanceCard(
                        componentName = "Lab",
                        weekAttendedStateList = labWeekAttendedStateList,
                        onClick = {},
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