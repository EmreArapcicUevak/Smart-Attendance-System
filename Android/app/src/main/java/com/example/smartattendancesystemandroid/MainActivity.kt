package com.example.smartattendancesystemandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.smartattendancesystemandroid.ui.navigation.Navigation
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartAttendanceSystemAndroidTheme {
                Navigation()
            }
        }
    }
}