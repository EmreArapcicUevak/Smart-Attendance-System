package com.example.smartattendancesystemandroid.ui.screens.markattendancescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.data.model.CourseType
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceReview(
    selectedWeek: Int = 1,
    attendancesTaken: Int = 0,
    selectedCourseComponent: CourseType = CourseType.LECTURE,
    onCancelAttendance: () -> Unit,
    onSubmitAttendance: () -> Unit,
) {

    val selectedCourseComponentText = when(selectedCourseComponent) {
        CourseType.LECTURE -> "Lecture"
        CourseType.TUTORIAL -> "Tutorial"
        CourseType.LAB -> "Lab"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Attendance Review")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(12.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Card (
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = "Summary :",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row {
                        Text(text = "Selected week : ")
                        Text(
                            text = selectedWeek.toString(),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row {
                        Text(text = "Selected course component : ")
                        Text(
                            text = selectedCourseComponentText,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row {
                        Text(text = "Attendances taken : ")
                        Text(
                            text = attendancesTaken.toString(),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onCancelAttendance,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(
                        text = "Cancel",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                FilledTonalButton(
                    onClick = onSubmitAttendance,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(
                        text = "Submit",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AttendanceReviewPreview() {
    SmartAttendanceSystemAndroidTheme {
        AttendanceReview(
            onCancelAttendance = {},
            onSubmitAttendance = {}
        )
    }
}