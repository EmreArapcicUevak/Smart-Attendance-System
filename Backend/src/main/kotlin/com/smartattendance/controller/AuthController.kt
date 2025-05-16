package main.kotlin.com.example.smartattendance.controller

import main.kotlin.com.example.smartattendance.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import main.kotlin.com.smartattendance.controller.AuthenticationRequest
import main.kotlin.com.smartattendance.controller.AuthenticationResponse

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequest
    ): AuthenticationResponse =
        authenticationService.authenticate(authRequest)
}


