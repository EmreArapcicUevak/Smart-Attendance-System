package com.example.smartattendancesystemandroid.ui.screens.editcoursescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartattendancesystemandroid.data.model.CourseResponse
import com.example.smartattendancesystemandroid.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EditCourseScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(EditCourseScreenUiState())
    val uiState: StateFlow<EditCourseScreenUiState> = _uiState.asStateFlow()

    fun setup(courseId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                courseId = courseId
            )
        }

        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            val data = dataRepository.getStaffCourses()
            if (data.courses.isEmpty()) return@launch
            val courses = data.courses.filter { course ->
                course.courseId == _uiState.value.courseId
            }
            val course: CourseResponse = courses[0]

            _uiState.update { currentState ->
                currentState.copy(
                    courseName = course.courseName,
                    courseFaculty = course.courseFaculty,
                    courseCode = course.courseCode,
                    hasTutorials = course.hasTutorial,
                    hasLabs = course.hasLab,
                    isLoading = false
                )
            }
        }
    }

    fun onTabItemPressed (value: EditCourseScreenTab) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTab = value
            )
        }
    }

    fun checkIfCanUpdate() {
        if (
            _uiState.value.courseName.isNotEmpty()  &&
            _uiState.value.courseCode.isNotEmpty()  &&
            _uiState.value.courseFaculty.isNotEmpty()
        ) {
            _uiState.update { currentState ->
                currentState.copy(
                    canUpdate = true
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    canUpdate = false
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
        checkIfCanUpdate()
    }

    fun onCourseCodeValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                courseCode = value
            )
        }
        checkIfCanUpdate()
    }

    fun onCourseFacultyValueChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                courseFaculty = value
            )
        }
        checkIfCanUpdate()
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

    fun onEnrollFieldChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                enrollFieldValue = value
            )
        }

        if (_uiState.value.enrollFieldValue.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    canEnroll = false
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    canEnroll = true
                )
            }
        }
    }

    fun onWithdrawFieldChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                withdrawFieldValue = value
            )
        }

        if (_uiState.value.withdrawFieldValue.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    canWithdraw = false
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    canWithdraw = true
                )
            }
        }
    }

    fun openConfirmDeletionDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                isDialogOpen = true
            )
        }
    }

    fun closeConfirmDeletionDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                isDialogOpen = false
            )
        }
    }

    fun updateCourse() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            val body = CourseResponse(
                courseId = _uiState.value.courseId,
                courseName = _uiState.value.courseName,
                courseCode = _uiState.value.courseCode,
                courseFaculty = _uiState.value.courseFaculty,
                hasLab = _uiState.value.hasLabs,
                hasTutorial = _uiState.value.hasTutorials
            )

            val courseResponse = dataRepository.updateCourse(body)

            if (courseResponse.courseId == -1L) {
                throw Error("An error has occurred during update course api call")
            }

            _uiState.update { currentState ->
                currentState.copy(
                    closeScreen = true
                )
            }
        }
    }

    fun deleteCourse() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            dataRepository.deleteCourse(courseId = _uiState.value.courseId)

            _uiState.update { currentState ->
                currentState.copy(
                    closeScreen = true
                )
            }
        }
    }

    fun enrollStudent() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            dataRepository.enrollStudent(
                courseId = _uiState.value.courseId,
                studentId = _uiState.value.enrollFieldValue.toLong()
            )

            _uiState.update { currentState ->
                currentState.copy(
                    closeScreen = true
                )
            }
        }
    }

    fun withdrawStudent() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            dataRepository.withdrawStudent(
                courseId = _uiState.value.courseId,
                studentId = _uiState.value.withdrawFieldValue.toLong()
            )

            _uiState.update { currentState ->
                currentState.copy(
                    closeScreen = true
                )
            }
        }
    }
}