package com.example.smartattendancesystemandroid.ui.screens.createcoursescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartattendancesystemandroid.ui.components.LoadingCircleScreen
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun CreateCourseScreen(
    logoutPressed: () -> Unit = {},
    navigateBackPressed: () -> Unit = {},
    canNavigateBack: Boolean = false,
    createCourseScreenViewModel: CreateCourseScreenViewModel = hiltViewModel<CreateCourseScreenViewModel>()
) {

    val createCourseScreenUiState by createCourseScreenViewModel.uiState.collectAsState()

    if (createCourseScreenUiState.closeScreen) {
        navigateBackPressed()
        return
    }

    if (createCourseScreenUiState.isLoading) {
        LoadingCircleScreen()
        return
    }

    CreateCourseScreenComponent(
        logoutPressed = logoutPressed,
        canNavigateBack = canNavigateBack,
        navigateBackPressed = navigateBackPressed,
        courseNameValue = createCourseScreenUiState.courseName,
        onCourseNameValueChange = { createCourseScreenViewModel.onCourseNameValueChange(it) },
        courseCodeValue = createCourseScreenUiState.courseCode,
        onCourseCodeValueChange = { createCourseScreenViewModel.onCourseCodeValueChange(it) },
        courseFacultyValue = createCourseScreenUiState.courseFaculty,
        onCourseFacultyValueChange = { createCourseScreenViewModel.onCourseFacultyValueChange(it) },
        hasLabs = createCourseScreenUiState.hasLabs,
        hasTutorials = createCourseScreenUiState.hasTutorials,
        onTutorialCheckChange = { createCourseScreenViewModel.onTutorialCheckChange(it) },
        onLabCheckChange = { createCourseScreenViewModel.onLabCheckChange(it) },
        createCourse = { createCourseScreenViewModel.createCourse() },
        canCreate = createCourseScreenUiState.canCreate

    )
}

@Composable
private fun CreateCourseScreenComponent(
    logoutPressed: () -> Unit,
    navigateBackPressed: () -> Unit,
    canNavigateBack: Boolean,
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
    createCourse: () -> Unit = {},
    canCreate: Boolean = true,
) {
    Skeleton(
        topAppBarTitle = "Create Course",
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = courseNameValue,
                onValueChange = onCourseNameValueChange,
                label = { Text(text = "Course Name") },
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                OutlinedTextField(
                    value = courseCodeValue,
                    onValueChange = onCourseCodeValueChange,
                    label = { Text(text = "Course Code") },
                    maxLines = 1,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedTextField(
                    value = courseFacultyValue,
                    onValueChange = onCourseFacultyValueChange,
                    label = { Text(text = "Course Faculty") },
                    maxLines = 1,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Do you want tutorials ?")
                Switch(
                    checked = hasTutorials,
                    onCheckedChange = onTutorialCheckChange,
                    thumbContent = {if (hasTutorials) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    } else null},
                )
            }
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Do you want labs ?")
                Switch(
                    checked = hasLabs,
                    onCheckedChange = onLabCheckChange,
                    thumbContent = {if (hasLabs) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    } else null},
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            FilledTonalButton(
                onClick = createCourse,
                shape = RoundedCornerShape(8.dp),
                enabled = canCreate,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Create")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CreateCourseScreenComponentPreview() {
    SmartAttendanceSystemAndroidTheme {
        CreateCourseScreenComponent(
            canNavigateBack = true,
            logoutPressed = {},
            navigateBackPressed = {}
        )
    }
}