package com.example.smartattendancesystemandroid.ui.screens.login

data class LoginUiState(
    val emailFieldValue: String = "",
    val passwordFieldValue: String = "",
    val dialogOpen: Boolean = false,
    val isLoading: Boolean = false
)
