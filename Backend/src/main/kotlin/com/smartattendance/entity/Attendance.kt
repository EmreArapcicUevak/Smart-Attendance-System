package main.kotlin.com.smartattendance.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "attendance")
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    val student: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @Enumerated(EnumType.STRING)
    @Column(name = "component_type", nullable = false)
    val componentType: ComponentType,

    @Column(name = "date", nullable = false)
    val date: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: AttendanceStatus
)

enum class ComponentType {
    COURSE, TUTORIAL, LAB
}

enum class AttendanceStatus {
    PRESENT, ABSENT
}