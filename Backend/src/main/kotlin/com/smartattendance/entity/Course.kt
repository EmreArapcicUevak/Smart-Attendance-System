package com.smartattendance.entity

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

    @Column(name = "faculty", nullable = false)
    var faculty: String = "",

    @Column(name = "Instructor", nullable = false)
    val createdBy: String = "",

    @Column(name = "staff_id", nullable = false)
    val staffId: Long = 0,

    @Column(name = "has_labs", nullable = false)
    var hasLabs: Boolean = false,

    @Column(name = "has_tutorials", nullable = false)
    var hasTutorials: Boolean = false,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "course_students",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    var students: MutableSet<User> = mutableSetOf()

)

enum class DayOfTheWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}