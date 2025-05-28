package com.example.smartattendancesystemandroid.ui.screens.markattendancescreen

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
import kotlin.math.roundToInt

@HiltViewModel
class MarkAttendanceScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MarkAttendanceScreenUiState())
    val uiState: StateFlow<MarkAttendanceScreenUiState> = _uiState.asStateFlow()

    fun setup(courseId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                courseId = courseId
            )
        }
    }

    fun onSelectedCourseComponentChange(value: CourseType) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedComponent = value
            )
        }
    }

    fun onSliderValueChange(value: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                sliderPosition = value,
                sliderPositionInt = value.roundToInt()
            )
        }
    }

    fun startAttendanceTaking() {
        _uiState.update { currentState ->
            currentState.copy(
                markAttendanceState = MarkAttendanceState.CAMERA
            )
        }
    }

    fun resetAttendanceState() {
        _uiState.update { currentState ->
            currentState.copy(
                markAttendanceState = MarkAttendanceState.PRE,
                week = 1,
                selectedComponent = CourseType.LECTURE,
                sliderPosition = 1f,
                sliderPositionInt = 1,
                studentList = listOf()
            )
        }
    }

    fun uploadAttendance() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            val body = AttendanceRequest(
                courseId = _uiState.value.courseId,
                week = _uiState.value.week,
                courseType = _uiState.value.selectedComponent,
                status = WeekAttendedState.ATTENDED,
                attendees = _uiState.value.studentList
            )
            dataRepository.markAttendance(body)

            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    showDialog = true,
                )
            }
        }
    }

    fun onFinishTakingAttendance() {
        _uiState.update { currentState ->
            currentState.copy(
                markAttendanceState = MarkAttendanceState.POST
            )
        }
    }

    fun addAttendanceToTheList(value: Long) {
        val attendanceList = _uiState.value.studentList + value
        _uiState.update { currentState ->
            currentState.copy(
                studentList = attendanceList
            )
        }
    }

    fun onDialogConfirm() {
        _uiState.update { currentState ->
            currentState.copy(
                showDialog = false
            )
        }
    }

}