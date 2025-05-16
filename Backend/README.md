# Smart Attendance Backend

This is the backend for the Smart Attendance System, built with Kotlin, Spring Boot, and PostgreSQL.

## Getting Started

1. Configure your PostgreSQL database in `src/main/resources/application.properties`.
2. Build and run the project with Maven:
   ```bash
   mvn spring-boot:run
   ```

## Project Structure
- `entity/` - JPA entities
- `repository/` - Spring Data JPA repositories
- `controller/` - REST controllers
- `service/` - Business logic (optional, but recommended)

## Requirements
- Java 17+
- Maven
- PostgreSQL
