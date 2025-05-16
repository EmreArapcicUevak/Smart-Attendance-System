package main.kotlin.com.smartattendance.service

import main.kotlin.com.smartattendance.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = main.kotlin.com.smartattendance.entity.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found with email: $username")

    private fun ApplicationUser.mapToUserDetails(): UserDetails {
        val authorities = listOf(SimpleGrantedAuthority( this.role.name))
        return User.builder()
            .username(this.email)
            .password(this.password)
            .authorities(authorities)
            .build()
    }
}