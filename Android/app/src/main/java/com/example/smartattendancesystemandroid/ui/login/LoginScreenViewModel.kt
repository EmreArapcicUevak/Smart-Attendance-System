package com.example.smartattendancesystemandroid.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartattendancesystemandroid.auth.AuthRepository
import com.example.smartattendancesystemandroid.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(

) {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val authResultChannel = Channel<AuthResult<Unit>>()
    val authResult = authResultChannel.receiveAsFlow()

    init {
        authenticate()
    }

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

    fun loginBtnPressed() {
        login()
    }

    private fun login() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }

            val result = authRepository.login(
                email = _uiState.value.emailFieldValue,
                password = _uiState.value.passwordFieldValue
            )
            authResultChannel.send(result)

            _uiState.update { currentState ->
                currentState.copy(isLoading = false)
            }
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }

            val result = authRepository.authenticate()
            authResultChannel.send(result)

            _uiState.update { currentState ->
                currentState.copy(isLoading = false)
            }
        }
    }

}