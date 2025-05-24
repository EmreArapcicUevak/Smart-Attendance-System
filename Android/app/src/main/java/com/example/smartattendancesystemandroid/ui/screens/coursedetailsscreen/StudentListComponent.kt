package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

import com.example.smartattendancesystemandroid.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme
import com.example.smartattendancesystemandroid.ui.theme.suggestionChipFailureColor
import com.example.smartattendancesystemandroid.ui.theme.suggestionChipSuccessColor

@Composable
fun StudentListComponent(
    students: List<StudentCardData>,
    cardPressed: (Long) -> Unit = {},
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        items(students) { student ->
            StudentCard(
                id = student.id,
                name = student.name,
                studentId = student.studentId,
                lectureSection = student.lectureSection,
                tutorialSection = student.tutorialSection,
                labSection = student.labSection,
                lectureAttendancePercentage = student.lectureAttendancePercentage,
                tutorialAttendancePercentage = student.tutorialAttendancePercentage,
                labAttendancePercentage = student.labAttendancePercentage,
                lectureAttendanceMinPercentage = student.lectureAttendanceMinPercentage,
                tutorialAttendanceMinPercentage = student.tutorialAttendanceMinPercentage,
                labAttendanceMinPercentage = student.labAttendanceMinPercentage,
                cardPressed = cardPressed
            )
        }
    }
}


@Composable
private fun StudentCard(
    id: Long,
    name: String,
    studentId: Long,
    lectureSection: Int,
    tutorialSection: Int? = null,
    labSection: Int? = null,
    lectureAttendancePercentage: Double,
    tutorialAttendancePercentage: Double? = null,
    labAttendancePercentage: Double? = null,
    lectureAttendanceMinPercentage: Double,
    tutorialAttendanceMinPercentage: Double? = null,
    labAttendanceMinPercentage: Double? = null,
    cardPressed: (Long) -> Unit = {},
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
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = studentId.toString(),
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.student_icon),
                    contentDescription = null,
                    modifier = Modifier.size(65.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )

            }

            HorizontalDivider(modifier = Modifier.padding(4.dp))
            /*
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Lecture section $lectureSection",
                    style = MaterialTheme.typography.titleMedium,

                )
                SuggestionChip(
                    onClick = {/* Does nothing on click*/},
                    label = {
                        Text(
                            text = "$lectureAttendancePercentage% Attendance",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = if (lectureAttendancePercentage >= lectureAttendanceMinPercentage) suggestionChipSuccessColor else suggestionChipFailureColor,
                        labelColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    border = BorderStroke(3.dp, if (lectureAttendancePercentage >= lectureAttendanceMinPercentage) suggestionChipSuccessColor else suggestionChipFailureColor),
                    modifier = Modifier.width(155.dp)
                )
            }

            if (tutorialSection != null && tutorialAttendancePercentage != null && tutorialAttendanceMinPercentage != null) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tutorial section $tutorialSection",
                        style = MaterialTheme.typography.titleMedium
                    )
                    SuggestionChip(
                        onClick = {/* Does nothing on click*/},
                        label = {
                            Text(
                                text = "$tutorialAttendancePercentage% Attendance",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = if (tutorialAttendancePercentage >= tutorialAttendanceMinPercentage) suggestionChipSuccessColor else suggestionChipFailureColor,
                            labelColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        border = BorderStroke(3.dp, if (tutorialAttendancePercentage >= tutorialAttendanceMinPercentage) suggestionChipSuccessColor else suggestionChipFailureColor),
                        modifier = Modifier.width(155.dp)
                    )
                }
            }

            if (labSection != null && labAttendancePercentage != null && labAttendanceMinPercentage != null) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Lab section $labSection",
                        style = MaterialTheme.typography.titleMedium
                    )
                    SuggestionChip(
                        onClick = {/* Does nothing on click*/},
                        label = {
                            Text(
                                text = "$labAttendancePercentage% Attendance",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = if (labAttendancePercentage >= labAttendanceMinPercentage) suggestionChipSuccessColor else suggestionChipFailureColor,
                            labelColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        border = BorderStroke(3.dp, if (labAttendancePercentage >= labAttendanceMinPercentage) suggestionChipSuccessColor else suggestionChipFailureColor),
                        modifier = Modifier.width(155.dp)
                    )
                }
            }
            */
        }

    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StudentListComponentPreview() {

    SmartAttendanceSystemAndroidTheme {
        StudentListComponent(
            students = getExampleStudentCardData()
        )
    }
}

@Preview
@Composable
private fun StudentCardPreview() {
    StudentCard(
        id = 0,
        name = "Vedad Šiljić",
        studentId = 220302211,
        lectureSection = 1,
        lectureAttendancePercentage = 80.2,
        lectureAttendanceMinPercentage = 70.0,
        tutorialSection = 2,
        tutorialAttendancePercentage = 20.5,
        tutorialAttendanceMinPercentage = 80.0,
        labSection = 3,
        labAttendancePercentage = 100.0,
        labAttendanceMinPercentage = 90.0

    )
}