package com.smartattendance.service

import com.smartattendance.config.JwtProperties
import com.smartattendance.controller.AuthenticationRequest
import com.smartattendance.controller.AuthenticationResponse
import com.smartattendance.repository.UserRepository
import com.smartattendance.service.CustomUserDetailsService
import com.smartattendance.service.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
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
            id = user.id,
            email = user.email,
            fullName = user.fullName,
            role = user.role,
        )

        return AuthenticationResponse(
            accessToken = accessToken
        )
    }
}