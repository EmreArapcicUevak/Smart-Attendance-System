package com.example.smartattendancesystemandroid.ui.screens.staffhomescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun StaffCourseList(
    staffCourses: List<CourseCardData> = listOf(),
    settingsBtnPressed: (String) -> Unit = {},
    cardPressed: (String) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Current Courses",
            style = MaterialTheme.typography.headlineSmall
        )

        Column(

        ) {
            for (course in staffCourses) {
                StaffCourseCard(
                    id = course.id,
                    courseName = course.courseName,
                    courseCode = course.courseCode,
                    courseFaculty = course.courseFaculty,
                    settingsBtnPressed = settingsBtnPressed,
                    cardPressed = cardPressed
                )
            }
        }
    }
}

@Composable
fun StaffCourseCard(

    courseName: String,
    courseCode: String,
    courseFaculty: String,
    settingsBtnPressed: (String) -> Unit,
    cardPressed: (String) -> Unit,
    id: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = {cardPressed(id)}),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = courseName,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = {settingsBtnPressed(id)}) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = courseCode,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = courseFaculty,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun CourseListPreview() {
    SmartAttendanceSystemAndroidTheme {
        StaffCourseList()
    }
}

@Preview(showBackground = false)
@Composable
private fun CardPreview() {
    SmartAttendanceSystemAndroidTheme {
        StaffCourseCard(
            id = "",
            courseName = "Software Engineering",
            courseCode = "CS308",
            courseFaculty = "FENS",
            settingsBtnPressed = {},
            cardPressed = {}
        )
    }
}