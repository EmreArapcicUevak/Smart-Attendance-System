package main.kotlin.com.smartattendance.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "\"user\"")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id: Long = 0,
    @Column(nullable = false)
    val organizationId: Long = 0,
    @Column(unique = true, nullable = false)
    val email: String = "",
    @Column(nullable = false)
    @JsonIgnore
    var password: String = "",
    @Column(nullable = false)
    val fullName: String = "",
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = Role.STUDENT,
    @Column(nullable = true)
    val studentId: String = "",
)

enum class Role {
    STUDENT, STAFF, ADMIN
}