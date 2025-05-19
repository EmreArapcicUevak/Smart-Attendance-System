package main.kotlin.com.smartattendance.service

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import java.util.*
import javax.crypto.SecretKey

object TokenService {
    private val SECRET: String = System.getenv("JWT_KEY")
    private val key: SecretKey = Keys.hmacShaKeyFor(SECRET.toByteArray())

    fun generateToken(email: String, fullName: String): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("fullName", fullName)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = extractAllClaims(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun extractEmail(token: String): String = extractAllClaims(token).subject
    fun extractFullName(token: String): String = extractAllClaims(token)["fullName"] as String

    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}