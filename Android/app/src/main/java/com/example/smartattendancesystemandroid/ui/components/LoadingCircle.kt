package com.example.smartattendancesystemandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun LoadingCircle() {
    CircularProgressIndicator(
       /* color = MaterialTheme.colorScheme.onPrimary,
        trackColor = MaterialTheme.colorScheme.primary,
        strokeWidth = 6.dp,*/
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun LoadingCircleScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        LoadingCircle()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoadingCircleScreenPreview() {
    SmartAttendanceSystemAndroidTheme {
        LoadingCircleScreen()
    }
}

@Preview
@Composable
private fun LoadingCirclePreview() {
    SmartAttendanceSystemAndroidTheme {
        LoadingCircle()
    }
}