package main.kotlin.com.smartattendance.dto

class OrganizationDto {

    data class OrganizationRequest(
        val name: String,
        val description: String?,
        val createdById: Long
    )

    data class OrganizationResponse(
        val id: Long,
        val name: String,
        val description: String?
    )
}