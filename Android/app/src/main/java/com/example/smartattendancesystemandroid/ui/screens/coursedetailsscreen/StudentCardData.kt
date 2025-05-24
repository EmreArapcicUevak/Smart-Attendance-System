package com.example.smartattendancesystemandroid.ui.screens.coursedetailsscreen

data class StudentCardData(
    val id: Long,
    val name: String,
    val studentId: Long,
    val lectureSection: Int,
    val tutorialSection: Int?,
    val labSection: Int?,
    val lectureAttendancePercentage: Double,
    val tutorialAttendancePercentage: Double?,
    val labAttendancePercentage: Double?,
    val lectureAttendanceMinPercentage: Double,
    val tutorialAttendanceMinPercentage: Double?,
    val labAttendanceMinPercentage: Double?,
)

fun getExampleStudentCardData(): List<StudentCardData> {
    return listOf(
        StudentCardData(1, "Amar Hodžić", 220302211, 1, 2, 1, 92.5, 88.0, 95.0, 75.0, 70.0, 80.0),
        StudentCardData(2, "Lejla Mujkić", 220302212, 1, 2, 1, 67.0, 62.5, 70.0, 75.0, 70.0, 80.0),
        StudentCardData(3, "Faruk Delić", 220302213, 2, 1, 2, 85.0, 78.0, 83.0, 75.0, 70.0, 80.0),
        StudentCardData(4, "Ajla Smajić", 220302214, 2, null, 1, 90.0, null, 89.0, 75.0, null, 80.0),
        StudentCardData(5, "Tarik Halilović", 220302215, 1, 1, null, 95.0, 92.0, null, 75.0, 70.0, null),
        StudentCardData(6, "Sara Mehić", 220302216, 3, 2, 2, 55.0, 60.0, 50.0, 75.0, 70.0, 80.0),
        StudentCardData(7, "Haris Begić", 220302217, 1, 1, 1, 100.0, 99.5, 100.0, 75.0, 70.0, 80.0),
        StudentCardData(8, "Amina Krlić", 220302218, 3, 2, null, 72.0, 65.0, null, 75.0, 70.0, null),
        StudentCardData(9, "Elma Jusić", 220302219, 2, null, null, 84.0, null, null, 75.0, null, null),
        StudentCardData(10, "Adnan Ibrišimović", 220302220, 1, 2, 2, 76.0, 74.0, 79.0, 75.0, 70.0, 80.0),
        StudentCardData(11, "Selma Latić", 220302221, 3, 3, 3, 68.5, 66.0, 70.5, 75.0, 70.0, 80.0),
        StudentCardData(12, "Kenan Hadžić", 220302222, 1, null, 1, 90.0, null, 85.0, 75.0, null, 80.0),
        StudentCardData(13, "Lamija Topalović", 220302223, 2, 2, null, 88.0, 80.0, null, 75.0, 70.0, null),
        StudentCardData(14, "Dino Avdić", 220302224, 1, 1, 1, 94.0, 91.0, 93.5, 75.0, 70.0, 80.0),
        StudentCardData(15, "Maja Zukić", 220302225, 2, 1, 1, 59.0, 58.0, 60.0, 75.0, 70.0, 80.0),
        StudentCardData(16, "Nedim Džafić", 220302226, 3, 2, null, 77.0, 79.0, null, 75.0, 70.0, null),
        StudentCardData(17, "Ena Hadžimehmedović", 220302227, 1, null, 1, 66.0, null, 61.0, 75.0, null, 80.0),
        StudentCardData(18, "Ajdin Zubčević", 220302228, 3, 3, 3, 82.0, 85.0, 84.0, 75.0, 70.0, 80.0),
        StudentCardData(19, "Ilda Karić", 220302229, 2, 2, 2, 97.0, 96.0, 98.0, 75.0, 70.0, 80.0),
        StudentCardData(20, "Mirza Softić", 220302230, 1, 1, 1, 70.0, 72.0, 69.0, 75.0, 70.0, 80.0)
    )
}