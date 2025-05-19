package main.kotlin.com.example.smartattendance.service

import main.kotlin.com.smartattendance.config.JwtProperties
import main.kotlin.com.smartattendance.controller.AuthenticationRequest
import main.kotlin.com.smartattendance.controller.AuthenticationResponse
import main.kotlin.com.smartattendance.repository.UserRepository
import main.kotlin.com.smartattendance.service.CustomUserDetailsService
import main.kotlin.com.smartattendance.service.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository
) {
    fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val userDetails = userDetailsService.loadUserByUsername(authRequest.email)
        val user = userRepository.findByEmail(authRequest.email)
            ?: throw IllegalArgumentException("User not found")

        val accessToken = tokenService.generateToken(
            email = user.email,
            fullName = user.fullName
        )

        return AuthenticationResponse(
            accessToken = accessToken
        )
    }
}