package com.example.smartattendancesystemandroid.ui.screens.markattendancescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.data.model.CourseType
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun AttendanceSetup(
    courseName: String = "Mark Attendance",
    logoutPressed: () -> Unit = {},
    navigateBackPressed: () -> Unit = {},
    canNavigateBack: Boolean = true,
    onValueChange: (CourseType) -> Unit,
    startAttendanceTaking: () -> Unit,
    sliderPosition: Float,
    sliderPositionInt: Int,
    onSliderValueChange: (Float) -> Unit
) {
    Skeleton(
        topAppBarTitle = courseName,
        logoutPressed = logoutPressed,
        navigateBackPressed = navigateBackPressed,
        canNavigateBack = canNavigateBack,
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            SelectWeekSlider(
                sliderPosition = sliderPosition,
                sliderPositionInt = sliderPositionInt,
                onSliderValueChange = onSliderValueChange
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Text("Selected Component")
                Spacer(modifier = Modifier.width(20.dp))
                SelectCourseType(onValueChange = onValueChange)
            }
            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(
                onClick = startAttendanceTaking,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.width(120.dp)
            ) {
                Text(
                    text = "Start",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SelectWeekSlider(
    sliderPosition: Float,
    sliderPositionInt: Int,
    onSliderValueChange: (Float) -> Unit,
) {
    Column {
        Text(text = "Selected Week $sliderPositionInt")
        Slider(
            value = sliderPosition,
            onValueChange = onSliderValueChange,
            steps = 13,
            valueRange = 1f..15f
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectCourseType(
    onValueChange: (CourseType) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var value by remember {mutableStateOf("Lecture")}
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.width(200.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
                value = value,
                onValueChange = {},
                readOnly = true,
                colors = if (!expanded) ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ) else ExposedDropdownMenuDefaults.textFieldColors(),
                shape = RoundedCornerShape(4.dp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text("Lecture")
                    },
                    onClick = {
                        value = "Lecture"
                        onValueChange(CourseType.LECTURE)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )

                DropdownMenuItem(
                    text = {
                        Text("Tutorial")
                    },
                    onClick = {
                        value = "Tutorial"
                        onValueChange(CourseType.TUTORIAL)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )

                DropdownMenuItem(
                    text = {
                        Text("Lab")
                    },
                    onClick = {
                        value = "Lab"
                        onValueChange(CourseType.LAB)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AttendanceSetupPreview() {
    SmartAttendanceSystemAndroidTheme {
        AttendanceSetup(
            onValueChange = {},
            startAttendanceTaking = {},
            sliderPosition = 1f,
            sliderPositionInt = 1,
            onSliderValueChange = {}
        )
    }
}