package main.kotlin.com.smartattendance.service

import main.kotlin.com.smartattendance.dto.OrganizationRequest
import main.kotlin.com.smartattendance.dto.OrganizationResponse
import main.kotlin.com.smartattendance.entity.Organization
import main.kotlin.com.smartattendance.repository.OrganizationRepository
import main.kotlin.com.smartattendance.repository.UserRepository
import main.kotlin.com.smartattendance.entity.Role
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class OrganizationService(
    private val organizationRepository: OrganizationRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    open fun createOrganization(request: OrganizationRequest): OrganizationResponse {
        if (request.name.isBlank()) throw IllegalArgumentException("Organization name is required")
        if (organizationRepository.existsByName(request.name)) throw IllegalArgumentException("Organization name already exists")
        val admin = userRepository.findById(request.createdById)
            .orElseThrow { IllegalArgumentException("Invalid admin user ID") }
        if (admin.role != Role.ADMIN ) {
            throw IllegalArgumentException("User does not have ADMIN role")
        }
        val org = Organization(
            name = request.name,
            description = request.description,
            createdBy = admin
        )
        val saved = organizationRepository.save(org)
        return OrganizationResponse(saved.id, saved.name, saved.description)
    }
}