package com.example.smartattendancesystemandroid.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailFieldValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(emailFieldValue = value)
        }
    }

    fun onPasswordFieldValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(passwordFieldValue = value)
        }
    }

    fun openDialog(value: Boolean = true) {
        _uiState.update { currentState ->
            currentState.copy(dialogOpen = value)
        }
    }

    fun login() {

    }

}