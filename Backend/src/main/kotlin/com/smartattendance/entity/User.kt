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
    @Column(nullable = true)
    val role: Role = Role.STUDENT,
)

enum class Role {
    STUDENT, TEACHER, ADMIN
}