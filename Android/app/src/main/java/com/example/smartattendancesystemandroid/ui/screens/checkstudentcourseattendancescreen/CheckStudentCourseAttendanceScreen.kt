package com.example.smartattendancesystemandroid.ui.screens.checkstudentcourseattendancescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.data.model.WeekAttendedState
import com.example.smartattendancesystemandroid.data.model.getWeekAttendedStateExamples
import com.example.smartattendancesystemandroid.ui.components.CourseComponentAttendanceCard
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme
import com.example.smartattendancesystemandroid.ui.theme.suggestionChipFailureColor
import com.example.smartattendancesystemandroid.ui.theme.suggestionChipSuccessColor
import com.example.smartattendancesystemandroid.ui.theme.suggestionColorChipNeutral

@Composable
fun CheckStudentCourseAttendanceScreen() {
    CheckStudentCourseAttendanceScreenContent(
        studentName = "Vedad Siljic",
        lectureWeekAttendedStateList = getWeekAttendedStateExamples(WeekAttendedState.NOT_MARKED),
        tutorialWeekAttendedStateList = getWeekAttendedStateExamples(WeekAttendedState.NOT_MARKED),
        labWeekAttendedStateList = getWeekAttendedStateExamples(),
    )
}

@Composable
fun CheckStudentCourseAttendanceScreenContent(
    studentName: String,
    logoutPressed: () -> Unit = {},
    canNavigateBack: Boolean = true,
    navigateBackPressed: () -> Unit = {},
    onWeekClicked: (Int) -> Unit = {},
    lectureWeekAttendedStateList: List<WeekAttendedState>,
    tutorialWeekAttendedStateList: List<WeekAttendedState>? = null,
    labWeekAttendedStateList: List<WeekAttendedState>? = null,
    dialogOpen: Boolean = true,
    onDialogClose: () -> Unit = {},
    lastClickedWeekState: WeekAttendedState = WeekAttendedState.NOT_MARKED,
) {
    Skeleton(
        topAppBarTitle = studentName,
        logoutPressed = logoutPressed,
        canNavigateBack = canNavigateBack,
        navigateBackPressed = navigateBackPressed
    ) {

        LazyColumn {
            items(1) {

                if (dialogOpen) {
                    ChangeStateDialog(
                        onClose = onDialogClose,
                        currentState = lastClickedWeekState
                    )
                }

                CourseComponentAttendanceCard(
                    componentName = "Lecture",
                    weekAttendedStateList = lectureWeekAttendedStateList,
                    onClick = onWeekClicked
                )

                if (tutorialWeekAttendedStateList != null) {
                    CourseComponentAttendanceCard(
                        componentName = "Tutorial",
                        weekAttendedStateList = tutorialWeekAttendedStateList,
                        onClick = onWeekClicked
                    )
                }

                if (labWeekAttendedStateList != null) {
                    CourseComponentAttendanceCard(
                        componentName = "Lab",
                        weekAttendedStateList = labWeekAttendedStateList,
                        onClick = onWeekClicked
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChangeStateDialog(
    modifier: Modifier = Modifier,
    currentState: WeekAttendedState = WeekAttendedState.NOT_MARKED,
    onClose: () -> Unit = {},

    ) {

    var selectedState by remember { mutableStateOf(currentState) }

    BasicAlertDialog(
        onDismissRequest = onClose,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.background(color = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = "Change State",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(4.dp)
            )
            SuggestionChip(
                onClick = { selectedState = WeekAttendedState.NOT_MARKED },
                label = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Not Marked")
                    }
                },
                colors = when (selectedState) {
                    WeekAttendedState.NOT_MARKED -> SuggestionChipDefaults.suggestionChipColors(
                        containerColor = suggestionColorChipNeutral,
                        labelColor = Color.White

                    )

                    else -> SuggestionChipDefaults.suggestionChipColors()
                },
                border = SuggestionChipDefaults.suggestionChipBorder(selectedState != WeekAttendedState.NOT_MARKED),
                modifier = Modifier.width(110.dp)
            )
            SuggestionChip(
                onClick = { selectedState = WeekAttendedState.MISSED },
                label = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text("Missed")
                    }
                },
                colors = when (selectedState) {
                    WeekAttendedState.MISSED -> SuggestionChipDefaults.suggestionChipColors(
                        containerColor = suggestionChipFailureColor,
                        labelColor = Color.White

                    )

                    else -> SuggestionChipDefaults.suggestionChipColors()
                },
                border = SuggestionChipDefaults.suggestionChipBorder(selectedState != WeekAttendedState.MISSED),
                modifier = Modifier.width(110.dp)
            )
            SuggestionChip(
                onClick = { selectedState = WeekAttendedState.ATTENDED },
                label = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text("Attended")
                    }
                },
                colors = when (selectedState) {
                    WeekAttendedState.ATTENDED -> SuggestionChipDefaults.suggestionChipColors(
                        containerColor = suggestionChipSuccessColor,
                        labelColor = Color.White

                    )

                    else -> SuggestionChipDefaults.suggestionChipColors()
                },
                border = SuggestionChipDefaults.suggestionChipBorder(selectedState != WeekAttendedState.ATTENDED),
                modifier = Modifier.width(110.dp)
            )

            Row {
                Button(
                    onClick = onClose,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Cancel")
                }

                Spacer(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckStudentCourseAttendanceScreenPreview() {
    SmartAttendanceSystemAndroidTheme {
        CheckStudentCourseAttendanceScreenContent(
            studentName = "Vedad Siljic",
            lectureWeekAttendedStateList = getWeekAttendedStateExamples(WeekAttendedState.NOT_MARKED),
            tutorialWeekAttendedStateList = getWeekAttendedStateExamples(WeekAttendedState.NOT_MARKED),
            labWeekAttendedStateList = getWeekAttendedStateExamples()
        )
    }
}

@Preview
@Composable
private fun ChangeStateDialogPreview() {
    SmartAttendanceSystemAndroidTheme {
        ChangeStateDialog()
    }
}

