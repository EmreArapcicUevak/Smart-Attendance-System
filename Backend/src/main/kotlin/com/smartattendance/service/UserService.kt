package com.smartattendance.service


import com.smartattendance.entity.User
import com.smartattendance.entity.Role
import org.springframework.security.crypto.password.PasswordEncoder
import com.smartattendance.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {
    fun createUser(user: User): User? {
        val found = userRepository.findByEmail(user.email)

        return if (found == null) {
            user.password = encoder.encode(user.password)
            userRepository.save(user)
        } else {
            null
        }
    }

    fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findAllStudents(): List<User> {
        return userRepository.findAll().filter { it.role == Role.STUDENT }
    }

    fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }
}
