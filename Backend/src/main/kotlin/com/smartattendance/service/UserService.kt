package main.kotlin.com.smartattendance.service


import main.kotlin.com.smartattendance.entity.User
import org.springframework.security.crypto.password.PasswordEncoder
import main.kotlin.com.smartattendance.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt
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

    fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }
}
