package com.example.smartattendancesystemandroid.ui.screens.editcoursescreen

import androidx.compose.foundation.background
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
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun CourseProperties(
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
    updateCourse: () -> Unit = {},
    canUpdate: Boolean = true,
    deleteCourse: () -> Unit = {},
    isDialogOpen: Boolean = false,
    closeConfirmDeletionDialog: () -> Unit = {},
    openConfirmDeletionDialog: () -> Unit = {}
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
            onClick = updateCourse,
            shape = RoundedCornerShape(8.dp),
            enabled = canUpdate,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update Course")
        }

        Spacer(modifier = Modifier.height(8.dp))
        FilledTonalButton(
            onClick = openConfirmDeletionDialog,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Delete Course")
        }
    }

    if (isDialogOpen) {
        DeleteCourseDialog(deleteCourse = deleteCourse, closeConfirmDeletionDialog = closeConfirmDeletionDialog)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeleteCourseDialog(deleteCourse: () -> Unit = {}, closeConfirmDeletionDialog: () -> Unit = {}) {

    BasicAlertDialog(onDismissRequest = closeConfirmDeletionDialog) {
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
                text = "Are you sure you want to delete this course ?",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 30.dp).padding(10.dp)
            )
            Row {
                OutlinedButton(
                    onClick = closeConfirmDeletionDialog,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(20.dp))
                FilledTonalButton(
                    onClick = deleteCourse,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent
                    ),
                ) {
                    Text(text = "Delete")
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeleteCourseDialogPreview() {
    SmartAttendanceSystemAndroidTheme {
        DeleteCourseDialog()
    }
}