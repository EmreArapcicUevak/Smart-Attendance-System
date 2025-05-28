package com.example.smartattendancesystemandroid.ui.screens.staffhomescreen

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
class StaffHomeScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
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

            val staffCourses = dataRepository.getStaffCourses()

            val staffCourseCards = staffCourses.courses.map { staffCourse ->
                CourseCardData(
                    id = staffCourse.courseId,
                    courseName = staffCourse.courseName,
                    courseCode = staffCourse.courseCode,
                    courseFaculty = staffCourse.courseFaculty
                )
            }

            _uiState.update { currentState ->
                currentState.copy(staffCourses = staffCourseCards, isLoading = false)
            }
        }
    }

}