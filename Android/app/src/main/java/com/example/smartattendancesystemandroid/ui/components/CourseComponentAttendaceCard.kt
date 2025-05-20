package com.example.smartattendancesystemandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.data.model.WeekAttendedState
import com.example.smartattendancesystemandroid.data.model.getWeekAttendedStateExamples
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme
import com.example.smartattendancesystemandroid.ui.theme.suggestionChipFailureColor
import com.example.smartattendancesystemandroid.ui.theme.suggestionChipSuccessColor
import com.example.smartattendancesystemandroid.ui.theme.suggestionColorChipNeutral

@Composable
fun CourseComponentAttendanceCard(
    componentName: String,
    weekAttendedStateList: List<WeekAttendedState>,
    onClick: (Int) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = componentName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(4.dp))
            Column {
                for (i in 0..2) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (j in 1..5) {
                            WeekChip(
                                weekNumber = i * 5 + j,
                                weekAttendedState = weekAttendedStateList[i * 5 + j - 1],
                                onClick = onClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WeekChip(
    modifier: Modifier = Modifier,
    weekNumber: Int,
    weekAttendedState: WeekAttendedState = WeekAttendedState.NOT_MARKED,
    onClick: (Int) -> Unit = {}
) {
    val containerColor: Color = when (weekAttendedState) {
        WeekAttendedState.NOT_MARKED -> suggestionColorChipNeutral
        WeekAttendedState.MISSED -> suggestionChipFailureColor
        WeekAttendedState.ATTENDED -> suggestionChipSuccessColor
    }


    SuggestionChip(
        onClick = { onClick(weekNumber) },
        label = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "$weekNumber",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = containerColor,
            labelColor = Color.White
        ),
        modifier = modifier.size(60.dp)
    )
}

@Preview
@Composable
private fun CourseComponentAttendanceCardPreview() {
    SmartAttendanceSystemAndroidTheme {
        CourseComponentAttendanceCard(
            componentName = "Lecture",
            weekAttendedStateList = getWeekAttendedStateExamples(state = WeekAttendedState.NOT_MARKED)
        )
    }
}

@Preview
@Composable
private fun WeekChipPreview() {
    WeekChip(weekNumber = 5, weekAttendedState = WeekAttendedState.MISSED)
}