package com.smartattendance.service

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import com.smartattendance.entity.Role
import java.util.*
import javax.crypto.SecretKey
import java.util.Base64
import org.springframework.stereotype.Service

@Service
object TokenService {
    private val SECRET: String = System.getenv("JWT_KEY")
        ?: throw IllegalStateException("JWT_KEY environment variable is not set")
    private val key: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET))

    fun generateToken(email: String, id: Long, fullName: String, role: Role): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("id", id)
            .claim("fullName", fullName)
            .claim("role", role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = extractAllClaims(token)
            !claims.expiration.before(Date())
        } catch (e: ExpiredJwtException) {
            false // Token is expired
        } catch (e: JwtException) {
            false // Token is invalid
        }
    }

    fun extractEmail(token: String): String = extractAllClaims(token).subject
    fun extractFullName(token: String): String = extractAllClaims(token)["fullName"] as String
    fun extractRole(token: String): String = extractAllClaims(token)["role"] as String
    fun extractId(token: String): Long = (extractAllClaims(token)["id"] as Number).toLong()

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}