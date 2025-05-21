package main.kotlin.com.smartattendance.entity

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "course_name", nullable = false)
    var courseName: String = "",

    @Column(name = "course_code", nullable = false)
    var courseCode: String = "",

    @Enumerated(EnumType.STRING)
    @Column(name = "day_od_the_week", nullable = false)
    var dayOfTheWeek: DayOfTheWeek = DayOfTheWeek.MONDAY,

    @Column(name = "created_by", nullable = false)
    val createdBy: String = "",
)

enum class DayOfTheWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}