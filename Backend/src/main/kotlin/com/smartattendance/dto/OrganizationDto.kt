package main.kotlin.com.smartattendance.dto

    data class OrganizationRequest(
        val name: String,
        val adminEmail: String,
        val adminPassword: String
    )

    data class OrganizationResponse(
        val id: Long,
        val name: String,
    )
