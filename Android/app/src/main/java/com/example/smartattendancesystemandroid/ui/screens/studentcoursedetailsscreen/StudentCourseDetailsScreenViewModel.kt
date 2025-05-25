package com.example.smartattendancesystemandroid.ui.screens.studentcoursedetailsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartattendancesystemandroid.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class StudentCourseDetailsScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(StudentCourseDetailsScreenUiState())
    val uiState : StateFlow<StudentCourseDetailsScreenUiState> = _uiState.asStateFlow()

    fun setup(studentId: Long, courseName: String, courseId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                studentId = studentId,
                courseName = courseName,
                courseId = courseId
            )
        }

        getStudentAttendance()
    }

    private fun getStudentAttendance() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            val studentAttendance = dataRepository.getStudentAttendance(
                courseId = _uiState.value.courseId,
                studentId = _uiState.value.studentId
            )

            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    lectureWeekAttendedStateList = studentAttendance.lecture,
                    tutorialWeekAttendedStateList = studentAttendance.tutorial,
                    labWeekAttendedStateList = studentAttendance.lab
                )
            }
        }
    }
}