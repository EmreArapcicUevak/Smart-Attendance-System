package com.smartattendance.controller

import com.smartattendance.dto.OrganizationRequest
import com.smartattendance.dto.OrganizationResponse
import com.smartattendance.dto.OrganizationUpdateRequest
import com.smartattendance.service.OrganizationService
import org.springframework.http.ResponseEntity
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

    @GetMapping
    fun getAllOrganizations(): ResponseEntity<List<OrganizationResponse>> {
        val organizations = organizationService.getAllOrganizations()
        return ResponseEntity.ok(organizations)
    }

    @PutMapping("/{id}")
    fun updateOrganization(
        @PathVariable id: Long,
        @RequestBody request: OrganizationUpdateRequest
    ): ResponseEntity<OrganizationResponse> =
        try {
            ResponseEntity.ok(organizationService.updateOrganization(id, request))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
}