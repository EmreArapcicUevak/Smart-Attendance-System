package com.example.smartattendancesystemandroid.ui.screens.staffhomescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartattendancesystemandroid.ui.StaffHomeScreen
import com.example.smartattendancesystemandroid.ui.components.Skeleton
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun StaffHomeScreen() {


    StaffHomeScreenContent(
        settingsPressed = {},
        logoutPressed = {},
        canNavigateBack = false
    )
}

@Composable
private fun StaffHomeScreenContent(
    settingsPressed: () -> Unit,
    logoutPressed: () -> Unit,
    canNavigateBack: Boolean,
) {
    Skeleton(
        settingsPressed = settingsPressed,
        logoutPressed = logoutPressed,
        canNavigateBack = canNavigateBack,
        topAppBarTitle = "Dashboard",

    ) {



    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StaffHomeScreenContentPreview() {
    SmartAttendanceSystemAndroidTheme {
        StaffHomeScreenContent(
            settingsPressed = {},
            logoutPressed = {},
            canNavigateBack = false
        )
    }
}