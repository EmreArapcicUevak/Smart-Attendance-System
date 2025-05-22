package main.kotlin.com.smartattendance.service

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import main.kotlin.com.smartattendance.entity.Role
import java.util.*
import javax.crypto.SecretKey
import java.util.Base64
import kotlin.text.get

object TokenService {
    private val SECRET: String = System.getenv("JWT_KEY")
        ?: throw IllegalStateException("JWT_KEY environment variable is not set")
    private val key: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET))

    fun generateToken(email: String, fullName: String, role: Role): String {
        return Jwts.builder()
            .setSubject(email)
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

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}