package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

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
class CourseDetailsScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {



    private val _uiState = MutableStateFlow(CourseDetailsUiState())
    val uiState : StateFlow<CourseDetailsUiState> = _uiState.asStateFlow()


    fun onFilterFieldValueChange (value: String) {

        val filteredStudents = _uiState.value.students.filter { student ->
            student.name.contains(value, true) || student.studentId.toString().contains(value, true)
        }

        _uiState.update { currentState ->
            currentState.copy(
                filterFieldValue = value,
                filteredStudents = filteredStudents
            )
        }
    }

    private fun getCourseStudents() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }

            val students = dataRepository.getCourseStudents(courseId = _uiState.value.courseId)
            val studentCards = students.students.map { student->
                StudentCardData(
                    id = student.id,
                    name = student.fullName,
                    studentId = student.id,
                    lectureSection = -1,
                    tutorialSection = null,
                    labSection = null,
                    lectureAttendancePercentage = 0.0,
                    lectureAttendanceMinPercentage = -1.0,
                    tutorialAttendancePercentage = 0.0,
                    tutorialAttendanceMinPercentage = -1.0,
                    labAttendancePercentage = 0.0,
                    labAttendanceMinPercentage = -1.0
                )
            }
            _uiState.update { currentState ->
                currentState.copy(
                    students = studentCards,
                    filteredStudents = studentCards,
                    isLoading = false
                )
            }
        }
    }

    fun setup(courseId: Long) {
        _uiState.update { currentState ->
            currentState.copy(courseId = courseId)
        }

        getCourseStudents()
    }

}