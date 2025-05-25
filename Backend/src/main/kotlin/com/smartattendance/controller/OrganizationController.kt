package main.kotlin.com.smartattendance.controller

import main.kotlin.com.smartattendance.dto.OrganizationRequest
import main.kotlin.com.smartattendance.dto.OrganizationResponse
import main.kotlin.com.smartattendance.service.OrganizationService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/organizations")
class OrganizationController(
    private val organizationService: OrganizationService
) {
    @PostMapping
    fun createOrganization(@RequestBody request: OrganizationRequest): ResponseEntity<OrganizationResponse> =
        try {
            ResponseEntity.ok(organizationService.createOrganization(request))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
}