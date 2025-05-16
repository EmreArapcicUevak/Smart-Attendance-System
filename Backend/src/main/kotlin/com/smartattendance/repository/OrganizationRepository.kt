package main.kotlin.com.smartattendance.repository

import main.kotlin.com.smartattendance.entity.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository : JpaRepository<Organization, Long> {
    fun existsByName(name: String): Boolean
}