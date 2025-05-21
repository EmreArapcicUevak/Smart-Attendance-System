package main.kotlin.com.smartattendance.controller

import main.kotlin.com.smartattendance.entity.User
import main.kotlin.com.smartattendance.repository.UserRepository
import main.kotlin.com.smartattendance.dto.UserRequest
import main.kotlin.com.smartattendance.service.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/register")
    fun register(@RequestBody request: UserRequest): ResponseEntity<Any> {
        if (userRepository.findByEmail(request.email) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists")

        val user = User(
            email = request.email,
            fullName = request.fullName,
            organizationId = request.organizationId,
            password = passwordEncoder.encode(request.password),
            role = request.role
        )
        userRepository.save(user)
        return ResponseEntity.ok("User registered successfully")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthenticationRequest): ResponseEntity<Any> {
        val user = userRepository.findByEmail(request.email)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password")

        if (!passwordEncoder.matches(request.password, user.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password")
        }

        val token = TokenService.generateToken(user.email, user.fullName, user.role)
        return ResponseEntity.ok(mapOf("token" to token))
    }

    @PostMapping("/validate-token")
    fun validateToken(@RequestBody token: Map<String, String>): ResponseEntity<Any> {
        val tokenValue = token["token"] ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is missing")

        return if (TokenService.validateToken(tokenValue)) {
            ResponseEntity.ok("Token is valid")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token is expired or invalid")
        }
    }
}


