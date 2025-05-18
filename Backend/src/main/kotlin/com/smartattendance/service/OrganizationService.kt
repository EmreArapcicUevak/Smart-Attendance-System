package main.kotlin.com.smartattendance.service

import main.kotlin.com.smartattendance.dto.OrganizationRequest
import main.kotlin.com.smartattendance.dto.OrganizationResponse
import main.kotlin.com.smartattendance.entity.Organization
import main.kotlin.com.smartattendance.repository.OrganizationRepository
import main.kotlin.com.smartattendance.repository.UserRepository
import main.kotlin.com.smartattendance.entity.Role
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class OrganizationService(
    private val organizationRepository: OrganizationRepository,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder

) {
    @Transactional
    open fun createOrganization(request: OrganizationRequest): OrganizationResponse {
        if (request.name.isBlank()) throw IllegalArgumentException("Organization name is required")
        if (organizationRepository.existsByName(request.name)) throw IllegalArgumentException("Organization name already exists")
        val admin = userRepository.findByEmail(request.adminEmail)
            ?: throw IllegalArgumentException("Invalid admin email")
        if (admin.role != Role.ADMIN) {
            throw IllegalArgumentException("User does not have ADMIN role")
        }
        val org = Organization(
            name = request.name,
            adminEmail = request.adminEmail,
            adminPassword = encoder.encode(request.adminPassword),
        )
        val saved = organizationRepository.save(org)
        return OrganizationResponse(saved.id, saved.name)
    }
}