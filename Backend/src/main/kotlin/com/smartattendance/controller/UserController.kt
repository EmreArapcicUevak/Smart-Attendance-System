package main.kotlin.com.smartattendance.controller

import main.kotlin.com.smartattendance.service.UserService
import main.kotlin.com.smartattendance.dto.UserRequest
import main.kotlin.com.smartattendance.dto.UserResponse
import main.kotlin.com.smartattendance.entity.User
import main.kotlin.com.smartattendance.entity.Role
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody userRequest: UserRequest): UserResponse? =
        userService.createUser(
            user = userRequest.toModel()
        )
            ?.toResponse()

    @GetMapping
    fun getAllUsers(): List<UserResponse> =
        userService.findAll()
            .map { it.toResponse() }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserResponse? =
        userService.findById(id)?.toResponse()

    @GetMapping("/students")
    fun getAllStudents(): List<UserResponse> =
        userService.findAllStudents()
            .map { it.toResponse() }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long): ResponseEntity<Void> {
        userService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    private fun UserRequest.toModel(): User {
        return User(
            organizationId = this.organizationId,
            email = this.email,
            password = this.password,
            fullName = this.fullName,
            role = Role.STUDENT,
        )
    }

    private fun User.toResponse(): UserResponse {
        return UserResponse(
            id = this.id,
            email = this.email,
            fullName = this.fullName,
        )
    }
}
