package com.smartattendance.service

import com.smartattendance.dto.OrganizationRequest
import com.smartattendance.dto.OrganizationResponse
import com.smartattendance.dto.OrganizationUpdateRequest
import com.smartattendance.entity.Organization
import com.smartattendance.repository.OrganizationRepository
import com.smartattendance.repository.UserRepository
import com.smartattendance.entity.Role
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

    open fun getAllOrganizations(): List<OrganizationResponse> {
        return organizationRepository.findAll().map { org ->
            OrganizationResponse(org.id, org.name)
        }
    }

    open fun updateOrganization(id: Long, request: OrganizationUpdateRequest): OrganizationResponse {
        val organization = organizationRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Organization not found") }

        if (request.name.isBlank()) throw IllegalArgumentException("Organization name is required")
        if (organizationRepository.existsByNameAndIdNot(request.name, id)) {
            throw IllegalArgumentException("Organization name already exists")
        }

        organization.name = request.name

        val updated = organizationRepository.save(organization)
        return OrganizationResponse(updated.id, updated.name)
    }
}