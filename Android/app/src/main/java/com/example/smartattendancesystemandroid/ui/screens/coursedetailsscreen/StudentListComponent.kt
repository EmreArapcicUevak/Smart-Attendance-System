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
    cardPressed: (String) -> Unit = {},
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
    id: String,
    name: String,
    studentId: String,
    lectureSection: Int,
    tutorialSection: Int? = null,
    labSection: Int? = null,
    lectureAttendancePercentage: Double,
    tutorialAttendancePercentage: Double? = null,
    labAttendancePercentage: Double? = null,
    lectureAttendanceMinPercentage: Double,
    tutorialAttendanceMinPercentage: Double? = null,
    labAttendanceMinPercentage: Double? = null,
    cardPressed: (String) -> Unit = {},
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
                        text = studentId,
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
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StudentListComponentPreview() {

    val studentList = listOf(
        StudentCardData("1", "Amar Hodžić", "220302211", 1, 2, 1, 92.5, 88.0, 95.0, 75.0, 70.0, 80.0),
        StudentCardData("2", "Lejla Mujkić", "220302212", 1, 2, 1, 67.0, 62.5, 70.0, 75.0, 70.0, 80.0),
        StudentCardData("3", "Faruk Delić", "220302213", 2, 1, 2, 85.0, 78.0, 83.0, 75.0, 70.0, 80.0),
        StudentCardData("4", "Ajla Smajić", "220302214", 2, null, 1, 90.0, null, 89.0, 75.0, null, 80.0),
        StudentCardData("5", "Tarik Halilović", "220302215", 1, 1, null, 95.0, 92.0, null, 75.0, 70.0, null),
        StudentCardData("6", "Sara Mehić", "220302216", 3, 2, 2, 55.0, 60.0, 50.0, 75.0, 70.0, 80.0),
        StudentCardData("7", "Haris Begić", "220302217", 1, 1, 1, 100.0, 99.5, 100.0, 75.0, 70.0, 80.0),
        StudentCardData("8", "Amina Krlić", "220302218", 3, 2, null, 72.0, 65.0, null, 75.0, 70.0, null),
        StudentCardData("9", "Elma Jusić", "220302219", 2, null, null, 84.0, null, null, 75.0, null, null),
        StudentCardData("10", "Adnan Ibrišimović", "220302220", 1, 2, 2, 76.0, 74.0, 79.0, 75.0, 70.0, 80.0),
        StudentCardData("11", "Selma Latić", "220302221", 3, 3, 3, 68.5, 66.0, 70.5, 75.0, 70.0, 80.0),
        StudentCardData("12", "Kenan Hadžić", "220302222", 1, null, 1, 90.0, null, 85.0, 75.0, null, 80.0),
        StudentCardData("13", "Lamija Topalović", "220302223", 2, 2, null, 88.0, 80.0, null, 75.0, 70.0, null),
        StudentCardData("14", "Dino Avdić", "220302224", 1, 1, 1, 94.0, 91.0, 93.5, 75.0, 70.0, 80.0),
        StudentCardData("15", "Maja Zukić", "220302225", 2, 1, 1, 59.0, 58.0, 60.0, 75.0, 70.0, 80.0),
        StudentCardData("16", "Nedim Džafić", "220302226", 3, 2, null, 77.0, 79.0, null, 75.0, 70.0, null),
        StudentCardData("17", "Ena Hadžimehmedović", "220302227", 1, null, 1, 66.0, null, 61.0, 75.0, null, 80.0),
        StudentCardData("18", "Ajdin Zubčević", "220302228", 3, 3, 3, 82.0, 85.0, 84.0, 75.0, 70.0, 80.0),
        StudentCardData("19", "Ilda Karić", "220302229", 2, 2, 2, 97.0, 96.0, 98.0, 75.0, 70.0, 80.0),
        StudentCardData("20", "Mirza Softić", "220302230", 1, 1, 1, 70.0, 72.0, 69.0, 75.0, 70.0, 80.0)
    )


    SmartAttendanceSystemAndroidTheme {
        StudentListComponent(
            students = studentList
        )
    }
}

@Preview
@Composable
private fun StudentCardPreview() {
    StudentCard(
        id = "",
        name = "Vedad Šiljić",
        studentId = "220302211",
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