package com.smartattendance.repository

import com.smartattendance.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String?): User?
    fun existsByEmail(email: String?): Boolean
    fun findByStudentId(studentId: Long): User?
}