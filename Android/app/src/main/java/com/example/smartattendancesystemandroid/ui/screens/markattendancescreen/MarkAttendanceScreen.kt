package com.example.smartattendancesystemandroid.ui.screens.markattendancescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun MarkAttendanceScreen(
    logoutPressed: () -> Unit,
    navigateBackPressed: () -> Unit,
    canNavigateBack: Boolean,
    markAttendanceScreenViewModel: MarkAttendanceScreenViewModel = hiltViewModel<MarkAttendanceScreenViewModel>()
) {

    val markAttendanceScreenUiState by markAttendanceScreenViewModel.uiState.collectAsState()

    if (markAttendanceScreenUiState.isLoading) {
        LoadingCircleScreen()
        return
    }

    if (markAttendanceScreenUiState.showDialog) {
        SuccessDialog(
            onConfirm = {
                markAttendanceScreenViewModel.onDialogConfirm()
                navigateBackPressed()
            }
        )
    }

    if (markAttendanceScreenUiState.markAttendanceState == MarkAttendanceState.PRE) {
        AttendanceSetup(
            logoutPressed = logoutPressed,
            navigateBackPressed = navigateBackPressed,
            canNavigateBack = canNavigateBack,
            onValueChange = { markAttendanceScreenViewModel.onSelectedCourseComponentChange(it) },
            startAttendanceTaking = { markAttendanceScreenViewModel.startAttendanceTaking() },
            sliderPosition = markAttendanceScreenUiState.sliderPosition,
            sliderPositionInt = markAttendanceScreenUiState.sliderPositionInt,
            onSliderValueChange = { markAttendanceScreenViewModel.onSliderValueChange(it) }
        )
        return
    }

    if (markAttendanceScreenUiState.markAttendanceState == MarkAttendanceState.CAMERA) {
        CameraScreen(
            onFinishTakingAttendance = { markAttendanceScreenViewModel.onFinishTakingAttendance() },
            addAttendanceToTheList = { markAttendanceScreenViewModel.addAttendanceToTheList(it) }
        )
        return
    }

    if (markAttendanceScreenUiState.markAttendanceState == MarkAttendanceState.POST) {
        AttendanceReview(
            selectedWeek = markAttendanceScreenUiState.week,
            attendancesTaken = markAttendanceScreenUiState.studentList.size,
            selectedCourseComponent = markAttendanceScreenUiState.selectedComponent,
            onCancelAttendance = {
                markAttendanceScreenViewModel.resetAttendanceState()
                navigateBackPressed()
            },
            onSubmitAttendance = {
                markAttendanceScreenViewModel.uploadAttendance()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SuccessDialog(
    onConfirm: () -> Unit = {}
) {
    BasicAlertDialog(onDismissRequest = onConfirm) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Attendance Successfully Uploaded",
                modifier = Modifier.padding(bottom = 30.dp)
            )
            Button(
                onClick = onConfirm,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Ok")
            }
        }
    }
}

@Preview
@Composable
private fun SuccessDialogPreview() {
    SmartAttendanceSystemAndroidTheme {
        SuccessDialog()
    }
}

