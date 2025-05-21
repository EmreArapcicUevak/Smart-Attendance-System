package main.kotlin.com.smartattendance.entity

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "course_name", nullable = false)
    val courseName: String = "",

    @Column(name = "course_code", nullable = false)
    val courseCode: String = "",

    @Column(name = "day_od_the_week", nullable = false)
    val dayOfTheWeek: WeekDay = WeekDay.MONDAY,

    @Column(name = "created_by", nullable = false)
    val createdBy: String = "",
)

enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}