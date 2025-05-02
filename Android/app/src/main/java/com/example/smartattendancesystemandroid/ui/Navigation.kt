package com.example.smartattendancesystemandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartattendancesystemandroid.ui.login.LoginScreen
import com.example.smartattendancesystemandroid.ui.staffhomepage.StaffHomePage
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
                navController.navigate(StaffHomePage) {
                    popUpTo(StaffHomePage) {
                        inclusive = true
                    }
                }
            })
        }
        composable<StaffHomePage> {
            StaffHomePage()
        }
    }
}

@Serializable
object LoginScreen

@Serializable
object StaffHomePage

/*
@Serializable
data class Example(
    val example: Int,
    val example2: String?
)
*/
