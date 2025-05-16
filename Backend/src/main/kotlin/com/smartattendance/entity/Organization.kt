package main.kotlin.com.smartattendance.entity

import jakarta.persistence.*

@Entity
@Table(name = "organizations", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    val description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    val createdBy: User
)