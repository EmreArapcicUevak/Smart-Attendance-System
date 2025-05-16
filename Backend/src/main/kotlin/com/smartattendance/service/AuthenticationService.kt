package main.kotlin.com.example.smartattendance.service

import main.kotlin.com.smartattendance.config.JwtProperties
import main.kotlin.com.smartattendance.controller.AuthenticationRequest
import main.kotlin.com.smartattendance.controller.AuthenticationResponse
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
) {
    fun authenticate(authRequest: AuthenticationRequest) : AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = tokenService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration),
        )

        return AuthenticationResponse(
            accessToken = accessToken,
            )
    }
}
