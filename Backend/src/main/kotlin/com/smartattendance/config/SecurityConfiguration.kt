package main.kotlin.com.smartattendance.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import main.kotlin.com.smartattendance.service.CustomUserDetailsService
import org.springframework.security.web.SecurityFilterChain

@Configuration
open class SecurityConfiguration(
    private val userDetailsService: CustomUserDetailsService
) {

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    open fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(JwtAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}