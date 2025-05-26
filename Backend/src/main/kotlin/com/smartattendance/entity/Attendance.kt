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
    var student: User? = null,

    @Column(name = "student_id", nullable = false)
    var studentId: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    var course: Course? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "component_type", nullable = false)
    var componentType: ComponentType = ComponentType.LECTURE,

    @Column(name = "week_num", nullable = false)
    var weekNumber: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: AttendanceStatus = AttendanceStatus.PRESENT
)

enum class ComponentType {
    LECTURE, TUTORIAL, LAB
}

enum class AttendanceStatus {
    PRESENT, ABSENT
}