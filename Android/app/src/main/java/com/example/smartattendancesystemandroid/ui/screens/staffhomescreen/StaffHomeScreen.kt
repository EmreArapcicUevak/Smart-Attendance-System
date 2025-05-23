package com.example.smartattendancesystemandroid.ui.screens.staffhomescreen

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun StaffHomeScreen() {


    StaffHomeScreenContent(
        logoutPressed = {},
        canNavigateBack = false,
        navigateBackPressed = {},
        courseSettingsPressed = {},
        courseCardPressed = {},
        addCourseBtnPressed = {},
        staffCourses = listOf()
    )
}

@Composable
private fun StaffHomeScreenContent(
    logoutPressed: () -> Unit,
    courseSettingsPressed: (String) -> Unit,
    courseCardPressed: (String) -> Unit,
    addCourseBtnPressed: () -> Unit,
    staffCourses: List<CourseCardData>,
    canNavigateBack: Boolean,
    navigateBackPressed: () -> Unit,
) {
    Skeleton(
        logoutPressed = logoutPressed,
        canNavigateBack = canNavigateBack,
        topAppBarTitle = "Dashboard",
        navigateBackPressed = navigateBackPressed,
        additionalActions = {
            IconButton(
                onClick = addCourseBtnPressed
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
        }

    ) {

        StaffCourseList(
            settingsBtnPressed = courseSettingsPressed,
            cardPressed = courseCardPressed,
            staffCourses = staffCourses
        )

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StaffHomeScreenContentPreview() {
    SmartAttendanceSystemAndroidTheme {

        val courses = listOf(
            CourseCardData(
                id = "1",
                courseName = "Introduction to Computer Science",
                courseCode = "CS101",
                courseFaculty = "FENS"
            ),
            CourseCardData(
                id = "2",
                courseName = "Psychology 101",
                courseCode = "PSY101",
                courseFaculty = "FASS"
            ),
            CourseCardData(
                id = "3",
                courseName = "Business Management",
                courseCode = "BUS201",
                courseFaculty = "FAP"
            ),
            CourseCardData(
                id = "4",
                courseName = "Academic Writing",
                courseCode = "ENG102",
                courseFaculty = "ELS"
            ),
            CourseCardData(
                id = "5",
                courseName = "Data Structures",
                courseCode = "CS202",
                courseFaculty = "FENS"
            )
        )


        StaffHomeScreenContent(
            logoutPressed = {},
            canNavigateBack = false,
            navigateBackPressed = {},
            courseSettingsPressed = {},
            courseCardPressed = {},
            addCourseBtnPressed = {},
            staffCourses = courses
        )
    }
}