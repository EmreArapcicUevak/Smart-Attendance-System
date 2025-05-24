package com.example.smartattendancesystemandroid.ui.screens.studenthomescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartattendancesystemandroid.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentHomeScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(StudentHomeScreenUiState())
    val uiState: StateFlow<StudentHomeScreenUiState> = _uiState.asStateFlow()

    init {
        loadStudentCourses()
    }

    private fun loadStudentCourses() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }

            val studentCourses = dataRepository.getStudentCourses()

            val studentCourseCards = studentCourses.courses.map { studentCourse ->
                StudentCourseCardData(
                    id = studentCourse.courseId,
                    courseName = studentCourse.courseName,
                    courseCode = studentCourse.courseCode,
                    courseFaculty = studentCourse.courseFaculty
                )
            }

            _uiState.update { currentState ->
                currentState.copy(studentCourses = studentCourseCards, isLoading = false)
            }
        }
    }
}