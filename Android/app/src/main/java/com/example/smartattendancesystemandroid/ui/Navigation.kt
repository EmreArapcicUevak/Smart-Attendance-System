package com.example.smartattendancesystemandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartattendancesystemandroid.ui.screens.login.LoginScreen
import com.example.smartattendancesystemandroid.ui.screens.staffhomescreen.StaffHomeScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginScreen
    ) {
        composable<LoginScreen> {
            LoginScreen(navigateToStaffHomePage = {
                navController.navigate(StaffHomeScreen) {
                    popUpTo(LoginScreen) {
                        inclusive = true
                    }
                }
            })
        }
        composable<StaffHomeScreen> {
            StaffHomeScreen()
        }
    }
}

@Serializable
object LoginScreen

@Serializable
object StaffHomeScreen

/*
@Serializable
data class Example(
    val example: Int,
    val example2: String?
)
*/
