package com.example.smartattendancesystemandroid.ui.screens.studenthomescreen

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun StudentCourseList(
    studentCourses: List<StudentCourseCardData> = listOf(),
    cardPressed: (Long) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Current Courses",
            style = MaterialTheme.typography.headlineSmall
        )

        Column {
            for (course in studentCourses) {
                StudentCourseCard(
                    id = course.id,
                    courseName = course.courseName,
                    courseCode = course.courseCode,
                    courseFaculty = course.courseFaculty,
                    cardPressed = cardPressed
                )
            }
        }
    }
}

@Composable
fun StudentCourseCard(
    courseName: String,
    courseCode: String,
    courseFaculty: String,
    cardPressed: (Long) -> Unit,
    id: Long
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


@Preview
@Composable
private fun StudentCourseListPreview() {
    SmartAttendanceSystemAndroidTheme {
        StudentCourseList()
    }
}

@Preview
@Composable
private fun StudentCourseCardPreview() {
    SmartAttendanceSystemAndroidTheme {
        StudentCourseCard(
            id = 0,
            courseName = "Software Engineering",
            courseCode = "CS308",
            courseFaculty = "FENS",
            cardPressed = {}
        )
    }
}