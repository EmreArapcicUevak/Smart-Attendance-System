package com.example.smartattendancesystemandroid.ui.screens.createcoursescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartattendancesystemandroid.data.model.CourseRequest
import com.example.smartattendancesystemandroid.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CreateCourseScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(CreateCourseScreenUiState())
    val uiState: StateFlow<CreateCourseScreenUiState> = _uiState.asStateFlow()

    fun checkIfCanCreate() {
        if (
            _uiState.value.courseName.isNotEmpty()  &&
            _uiState.value.courseCode.isNotEmpty()  &&
            _uiState.value.courseFaculty.isNotEmpty()
        ) {
            _uiState.update { currentState ->
                currentState.copy(
                    canCreate = true
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    canCreate = false
                )
            }
        }
    }

    fun onCourseNameValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                courseName = value
            )
        }
        checkIfCanCreate()
    }

    fun onCourseCodeValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                courseCode = value
            )
        }
        checkIfCanCreate()
    }

    fun onCourseFacultyValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                courseFaculty = value
            )
        }
        checkIfCanCreate()
    }

    fun onTutorialCheckChange(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                hasTutorials = value
            )
        }
    }

    fun onLabCheckChange(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                hasLabs = value
            )
        }
    }

    fun createCourse() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            val body = CourseRequest(
                courseName = _uiState.value.courseName,
                courseCode = _uiState.value.courseCode,
                courseFaculty = _uiState.value.courseFaculty,
                hasTutorial = _uiState.value.hasTutorials,
                hasLab = _uiState.value.hasLabs
            )

            val courseResponse = dataRepository.createCourse(body)

            if (courseResponse.courseId == -1L) {
                throw Error("An error has occurred during create course api call")
            }

            _uiState.update { currentState ->
                currentState.copy(
                    closeScreen = true
                )
            }
        }
    }

}