package main.kotlin.com.smartattendance.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "organizations", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false)
    val adminEmail: String = "",

    @Column(nullable = false)
    @JsonIgnore
    val adminPassword: String = "",
)