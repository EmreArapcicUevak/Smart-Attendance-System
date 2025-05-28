package com.example.smartattendancesystemandroid.ui.screens.editcoursescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun AddRemoveStudents(
    enrollFieldValue: String = "",
    onEnrollFieldChange: (String) -> Unit = {},
    withdrawFieldValue: String = "",
    onWithdrawFieldChange: (String) -> Unit = {},
    enrollStudent: () -> Unit = {},
    withdrawStudent: () -> Unit = {},
    canEnroll: Boolean = false,
    canWithdraw: Boolean = true,
) {
    Column {
        Card (

        ) {
            Column (
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Enroll Student",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = enrollFieldValue,
                    onValueChange = onEnrollFieldChange,
                    label = { Text(text = "Student ID") },
                    maxLines = 1,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                FilledTonalButton(
                    onClick = enrollStudent,
                    shape = RoundedCornerShape(8.dp),
                    enabled = canEnroll,
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f).align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Enroll",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Card (

        ) {
            Column (
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Withdraw Student",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = withdrawFieldValue,
                    onValueChange = onWithdrawFieldChange,
                    label = { Text(text = "Student ID") },
                    maxLines = 1,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                FilledTonalButton(
                    onClick = withdrawStudent,
                    shape = RoundedCornerShape(8.dp),
                    enabled = canWithdraw,
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f).align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Withdraw",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddRemoveStudentsPreview() {
    SmartAttendanceSystemAndroidTheme {
        AddRemoveStudents()
    }
}