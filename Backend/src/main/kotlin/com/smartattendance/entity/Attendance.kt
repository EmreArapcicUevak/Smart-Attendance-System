package com.smartattendance.entity

import com.smartattendance.entity.Course
import jakarta.persistence.*

@Entity
@Table(
    name = "attendance",
    uniqueConstraints = [
        UniqueConstraint(
            name = "unique_attendance",
            columnNames = ["student_id", "course_id", "component_type", "week_num"]
        )
    ]
)
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student", nullable = false)
    val student: User,

    @Column(name = "student_id", nullable = false)
    val studentId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @Enumerated(EnumType.STRING)
    @Column(name = "component_type", nullable = false)
    val componentType: ComponentType,

    @Column(name = "week_num", nullable = false)
    val weekNumber: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: AttendanceStatus
)

enum class ComponentType {
    LECTURE, TUTORIAL, LAB
}

enum class AttendanceStatus {
    PRESENT, ABSENT
}