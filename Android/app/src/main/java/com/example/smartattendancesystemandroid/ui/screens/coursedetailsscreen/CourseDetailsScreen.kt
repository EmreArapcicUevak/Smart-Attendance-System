package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartattendancesystemandroid.ui.components.LoadingCircleScreen
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun CourseDetailsScreen(
    logoutPressed: () -> Unit,
    navigateBackPressed: () -> Unit,
    canNavigateBack: Boolean,
    cardPressed: (Long) -> Unit,
    courseDetailsScreenViewModel: CourseDetailsScreenViewModel = hiltViewModel<CourseDetailsScreenViewModel>()
) {

    val courseDetailsUiState by courseDetailsScreenViewModel.uiState.collectAsState()


    if (courseDetailsUiState.isLoading) {
        LoadingCircleScreen()
        return
    }


    CourseDetailsScreenContent(
        courseId = courseDetailsUiState.courseId,
        courseCode = courseDetailsUiState.courseCode,
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack,
        students = courseDetailsUiState.filteredStudents,
        filterFieldValue = courseDetailsUiState.filterFieldValue,
        onFilterFieldValueChange = {it ->
            courseDetailsScreenViewModel.onFilterFieldValueChange(it)
        },
        cardPressed = cardPressed,
        canTakeAttendance = courseDetailsUiState.canTakeAttendance
    )
}

@Composable
private fun CourseDetailsScreenContent(
    courseId: Long,
    courseCode: String,
    logoutPressed: () -> Unit = {},
    navigateBackPressed: () -> Unit = {},
    canNavigateBack: Boolean = true,
    students: List<StudentCardData> = listOf<StudentCardData>(),
    cardPressed: (Long) -> Unit = {},
    filterFieldValue: String = "",
    onFilterFieldValueChange: (String) -> Unit = {},
    takeAttendancePressed: (courseId: Long) -> Unit = {},
    canTakeAttendance: Boolean = true,

    ) {
    Skeleton(
        topAppBarTitle = "$courseCode Details",
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack,
        floatingActionButton = {
            if (canTakeAttendance) {
                FloatingActionButton(
                    onClick = {takeAttendancePressed(courseId)},
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ThumbUp,
                        contentDescription = null
                    )
                }
            }
        }
    ) {

        FilterStudents(
            modifier = Modifier.padding(10.dp),
            filterValue = filterFieldValue,
            onFilterFieldValueChange = onFilterFieldValueChange,
        )

        StudentListComponent(
            students = students,
            cardPressed = cardPressed,
        )
    }
}

@Composable
private fun FilterStudents(
    modifier: Modifier = Modifier,
    filterValue: String = "",
    onFilterFieldValueChange: (String) -> Unit = {},
    ) {

    val focusRequester = remember { FocusRequester() }

    Card(
        modifier = modifier.fillMaxWidth()
    ) {

        TextField(
            value = filterValue,
            onValueChange = onFilterFieldValueChange,
            placeholder = { Text(text = "Filter Students") },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .focusRequester(focusRequester),
            trailingIcon = {
                IconButton(
                    onClick = {
                        focusRequester.requestFocus()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CourseDetailsScreenPreview() {



    SmartAttendanceSystemAndroidTheme {
        CourseDetailsScreenContent(
            courseId = 0,
            courseCode = "CS308",
            students = getExampleStudentCardData()
        )
    }
}

@Preview
@Composable
private fun FilterStudentsPreview() {
    SmartAttendanceSystemAndroidTheme {
        FilterStudents(filterValue = "test")
    }
}