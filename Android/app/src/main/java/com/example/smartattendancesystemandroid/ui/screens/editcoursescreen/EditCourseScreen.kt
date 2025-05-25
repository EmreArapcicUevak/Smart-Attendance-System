package com.example.smartattendancesystemandroid.ui.screens.editcoursescreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartattendancesystemandroid.ui.components.LoadingCircleScreen
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun EditCourseScreen(
    logoutPressed: () -> Unit,
    navigateBackPressed: () -> Unit,
    canNavigateBack: Boolean,
    editCourseScreenViewModel: EditCourseScreenViewModel = hiltViewModel<EditCourseScreenViewModel>()
) {
    val editCourseScreenUiState by editCourseScreenViewModel.uiState.collectAsState()

    if (editCourseScreenUiState.closeScreen) {
        navigateBackPressed()
        return
    }

    if (editCourseScreenUiState.isLoading) {
        LoadingCircleScreen()
        return
    }

    EditCourseScreenContent(
        courseCode = editCourseScreenUiState.courseCode,
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack,
        selectedTab = editCourseScreenUiState.selectedTab,
        onTabItemPressed = { editCourseScreenViewModel.onTabItemPressed(it) },
        courseNameValue = editCourseScreenUiState.courseName,
        onCourseNameValueChange = { editCourseScreenViewModel.onCourseNameValueChange(it) },
        courseCodeValue = editCourseScreenUiState.courseCode,
        onCourseCodeValueChange = { editCourseScreenViewModel.onCourseCodeValueChange(it) },
        courseFacultyValue = editCourseScreenUiState.courseFaculty,
        onCourseFacultyValueChange = { editCourseScreenViewModel.onCourseFacultyValueChange(it) },
        hasLabs = editCourseScreenUiState.hasLabs,
        hasTutorials = editCourseScreenUiState.hasTutorials,
        onTutorialCheckChange = { editCourseScreenViewModel.onTutorialCheckChange(it) },
        onLabCheckChange = { editCourseScreenViewModel.onLabCheckChange(it) },
        updateCourse = { editCourseScreenViewModel.updateCourse() },
        canUpdate = editCourseScreenUiState.canUpdate,
        deleteCourse = { editCourseScreenViewModel.deleteCourse() },
        enrollStudent = {editCourseScreenViewModel.enrollStudent()},
        withdrawStudent = {editCourseScreenViewModel.withdrawStudent()},
        onEnrollFieldChange = {editCourseScreenViewModel.onEnrollFieldChange(it)},
        onWithdrawFieldChange = {editCourseScreenViewModel.onWithdrawFieldChange(it)},
        isDialogOpen = editCourseScreenUiState.isDialogOpen,
        closeConfirmDeletionDialog = { editCourseScreenViewModel.closeConfirmDeletionDialog() },
        openConfirmDeletionDialog = { editCourseScreenViewModel.openConfirmDeletionDialog() }
    )
}

@Composable
private fun EditCourseScreenContent(
    courseCode: String = "CS302",
    logoutPressed: () -> Unit = {},
    navigateBackPressed: () -> Unit = {},
    canNavigateBack: Boolean = true,
    selectedTab: EditCourseScreenTab = EditCourseScreenTab.STUDENT,
    onTabItemPressed: (EditCourseScreenTab) -> Unit = {},
    courseNameValue: String = "",
    onCourseCodeValueChange: (String) -> Unit = {},
    courseCodeValue: String = "",
    onCourseNameValueChange: (String) -> Unit = {},
    courseFacultyValue: String = "",
    onCourseFacultyValueChange: (String) -> Unit = {},
    hasTutorials: Boolean = false,
    hasLabs: Boolean = false,
    onTutorialCheckChange: (Boolean) -> Unit = {},
    onLabCheckChange: (Boolean) -> Unit = {},
    updateCourse: () -> Unit = {},
    canUpdate: Boolean = true,
    deleteCourse: () -> Unit = {},
    enrollFieldValue: String = "",
    withdrawFieldValue: String = "",
    canEnroll: Boolean = false,
    canWithdraw: Boolean = false,
    onEnrollFieldChange: (String) -> Unit = {},
    onWithdrawFieldChange: (String) -> Unit = {},
    enrollStudent: () -> Unit = {},
    withdrawStudent: () -> Unit = {},
    isDialogOpen: Boolean = false,
    closeConfirmDeletionDialog: () -> Unit = {},
    openConfirmDeletionDialog: () -> Unit = {}
) {
    Skeleton(
        topAppBarTitle = "Edit $courseCode",
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack,
        bottomBar = {
            EditScreenNavigationBar(
                selectedTab = selectedTab,
                onTabItemPressed = onTabItemPressed
            )
        }
    ) {
        if (selectedTab == EditCourseScreenTab.MAIN) {
            CourseProperties(
                courseNameValue = courseNameValue,
                onCourseNameValueChange = onCourseNameValueChange,
                courseCodeValue = courseCodeValue,
                onCourseCodeValueChange = onCourseCodeValueChange,
                courseFacultyValue = courseFacultyValue,
                onCourseFacultyValueChange = onCourseFacultyValueChange,
                hasTutorials = hasTutorials,
                hasLabs = hasLabs,
                onTutorialCheckChange = onTutorialCheckChange,
                onLabCheckChange = onLabCheckChange,
                updateCourse = updateCourse,
                canUpdate = canUpdate,
                deleteCourse = deleteCourse,
                isDialogOpen = isDialogOpen,
                closeConfirmDeletionDialog = closeConfirmDeletionDialog,
                openConfirmDeletionDialog = openConfirmDeletionDialog
            )
        }

        if (selectedTab == EditCourseScreenTab.STUDENT) {
            AddRemoveStudents(
                enrollFieldValue = enrollFieldValue,
                withdrawFieldValue = withdrawFieldValue,
                canEnroll = canEnroll,
                canWithdraw = canWithdraw,
                onEnrollFieldChange = onEnrollFieldChange,
                onWithdrawFieldChange = onWithdrawFieldChange,
                enrollStudent = enrollStudent,
                withdrawStudent = withdrawStudent
            )
        }
    }
}

@Composable
private fun EditScreenNavigationBar(
    selectedTab: EditCourseScreenTab,
    onTabItemPressed: (EditCourseScreenTab) -> Unit,
) {
    NavigationBar(
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        NavigationBarItem(
            selected = selectedTab == EditCourseScreenTab.MAIN,
            onClick = { onTabItemPressed(EditCourseScreenTab.MAIN) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Course Properties")
            }
        )
        NavigationBarItem(
            selected = selectedTab == EditCourseScreenTab.STUDENT,
            onClick = { onTabItemPressed(EditCourseScreenTab.STUDENT) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Add/Remove Students")
            }
        )
    }
}

@Preview
@Composable
private fun EditCourseScreenContentPreview() {
    SmartAttendanceSystemAndroidTheme {
        EditCourseScreenContent()
    }
}