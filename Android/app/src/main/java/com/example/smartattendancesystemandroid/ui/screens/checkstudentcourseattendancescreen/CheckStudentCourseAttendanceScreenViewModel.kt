package com.example.smartattendancesystemandroid.ui.screens.checkstudentcourseattendancescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartattendancesystemandroid.data.model.AttendanceRequest
import com.example.smartattendancesystemandroid.data.model.CourseType
import com.example.smartattendancesystemandroid.data.model.WeekAttendedState
import com.example.smartattendancesystemandroid.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CheckStudentCourseAttendanceScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(CheckStudentCourseAttendanceScreenUiState())
    val uiState : StateFlow<CheckStudentCourseAttendanceScreenUiState> = _uiState.asStateFlow()

    fun onDialogClose() {
        _uiState.update { currentState ->
            currentState.copy(dialogOpen = false)
        }
    }

    fun onWeekClicked(week: Int, componentName: String) {
        val lastClickedWeekState = when(componentName) {
            "Lecture" -> _uiState.value.lectureWeekAttendedStateList[week - 1]
            "Tutorial" -> _uiState.value.tutorialWeekAttendedStateList?.get(week - 1) ?: WeekAttendedState.NOT_MARKED
            "Lab" -> _uiState.value.labWeekAttendedStateList?.get(week - 1) ?: WeekAttendedState.NOT_MARKED
            else -> throw Error("Invalid componentName parameter in onWeekClicked")
        }

        _uiState.update { currentState ->
            currentState.copy(
                dialogOpen = true,
                lastClickedWeekState = lastClickedWeekState,
                dialogSelectedWeek = week,
                dialogSelectedCourseComponent = componentName
            )
        }
    }

    fun onChangeWeekAttendance() {
        if (_uiState.value.dialogSelectedWeek == null || _uiState.value.dialogSelectedCourseComponent == null) {
            throw Error("onChangeWeekAttendance parameter is null")
        }

        markAttendance()

        _uiState.update { currentState ->
            currentState.copy(
                dialogOpen = false,
                dialogSelectedWeek = null,
                dialogSelectedCourseComponent = null
            )
        }

        getStudentAttendance()
    }

    fun setup(studentId: Long, studentName: String, courseId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                studentId = studentId,
                studentName = studentName,
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

    private fun markAttendance() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            if (_uiState.value.dialogSelectedWeek == null || _uiState.value.dialogSelectedCourseComponent == null) {
                throw Error("onChangeWeekAttendance parameter is null")
                return@launch
            }

            val body = AttendanceRequest(
                courseId = _uiState.value.courseId,
                week = _uiState.value.dialogSelectedWeek ?: 1,
                courseType = when(_uiState.value.dialogSelectedCourseComponent) {
                    "Lecture" -> CourseType.LECTURE
                    "Tutorial" -> CourseType.TUTORIAL
                    "Lab" -> CourseType.LAB
                    else -> throw Error("Invalid course type in markAttendance")
                },
                attendees = listOf(_uiState.value.studentId),
                status = _uiState.value.lastClickedWeekState
            )

            dataRepository.markAttendance(body = body)

            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = false
                )
            }
        }
    }

}