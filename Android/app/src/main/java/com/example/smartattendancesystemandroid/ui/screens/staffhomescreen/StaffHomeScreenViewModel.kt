package com.example.smartattendancesystemandroid.ui.screens.staffhomescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class StaffHomeScreenViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(StaffHomeScreenUiState())
    val uiState: StateFlow<StaffHomeScreenUiState> = _uiState.asStateFlow()

    init {
        loadStaffCourses()
    }

    private fun loadStaffCourses() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            
            // TODO get staff courses

            _uiState.update { currentState ->
                currentState.copy(isLoading = false)
            }
        }
    }

}