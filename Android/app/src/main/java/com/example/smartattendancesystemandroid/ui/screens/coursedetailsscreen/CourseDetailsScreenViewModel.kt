package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class CourseDetailsScreenViewModel @Inject constructor(

): ViewModel() {

    val students = getExampleStudentCardData() // load from server later



    private val _uiState = MutableStateFlow(CourseDetailsUiState())
    val uiState : StateFlow<CourseDetailsUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(filteredStudents = students)
        }
    }


    fun onFilterFieldValueChange (value: String) {

        val filteredStudents = students.filter { student ->
            student.name.contains(value, true) || student.studentId.contains(value, true)
        }

        _uiState.update { currentState ->
            currentState.copy(
                filterFieldValue = value,
                filteredStudents = filteredStudents
            )
        }
    }

}