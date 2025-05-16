package main.kotlin.com.smartattendance.config

import main.kotlin.com.smartattendance.entity.Role
import main.kotlin.com.smartattendance.entity.User
import main.kotlin.com.smartattendance.repository.UserRepository
import main.kotlin.com.smartattendance.service.UserService
import main.kotlin.com.smartattendance.service.CustomUserDetailsService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableConfigurationProperties(JwtProperties::class)
open class Configuration {

    @Bean
    open fun userDetailsService(
        userRepository: UserRepository
    ): UserDetailsService =
        CustomUserDetailsService(userRepository)

    @Bean
    open fun encoder(): PasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    open fun authenticationProvider(
        userRepository: UserRepository
    ): AuthenticationProvider =
        DaoAuthenticationProvider().also {
            it.setUserDetailsService(userDetailsService(userRepository))
            it.setPasswordEncoder(encoder())
        }

    @Bean
    open fun authenticationManager(
        config: AuthenticationConfiguration
    ): AuthenticationManager =
        config.authenticationManager

}