package com.example.smartattendancesystemandroid.data.model

enum class WeekAttendedState {
    ATTENDED,
    MISSED,
    NOT_MARKED
}

fun getWeekAttendedStateExamples(): List<WeekAttendedState> {
    return listOf<WeekAttendedState>(
        WeekAttendedState.ATTENDED,
        WeekAttendedState.MISSED,
        WeekAttendedState.NOT_MARKED,
        WeekAttendedState.ATTENDED,
        WeekAttendedState.NOT_MARKED,
        WeekAttendedState.ATTENDED,
        WeekAttendedState.MISSED,
        WeekAttendedState.NOT_MARKED,
        WeekAttendedState.ATTENDED,
        WeekAttendedState.MISSED,
        WeekAttendedState.ATTENDED,
        WeekAttendedState.NOT_MARKED,
        WeekAttendedState.NOT_MARKED,
        WeekAttendedState.ATTENDED,
        WeekAttendedState.MISSED
    )

}

fun getWeekAttendedStateExamples(state: WeekAttendedState): List<WeekAttendedState> {
    return listOf(
        state, state, state, state, state,
        state, state, state, state, state,
        state, state, state, state, state,
    )
}