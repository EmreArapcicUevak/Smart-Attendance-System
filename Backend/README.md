# Smart Attendance System – Backend

##  Overview
This is the backend repository for the **Smart Attendance System**, a secure and scalable solution for managing student attendance in educational institutions.

---

##  Features

- **Authentication & Authorization**  
  Secure login using JWT with role-based access control (`STUDENT`, `STAFF`, `ADMIN`).

- **Attendance Management**  
  View and manage attendance records for students.

- **Course Management**  
  Add, update, and validate course details.

- **User Management**  
  Create, retrieve, and delete users with role-based filtering.

---

##  Tech Stack

### Backend

- **Language:** Kotlin
- **Framework:** Spring Boot
- **Database:** PostgreSQL / MySQL
- **Build Tool:** Maven
- **Security:** Spring Security, JWT
- **ORM:** JPA / Hibernate

---

##  Project Structure
Backend/
│
├── controller/ # Handles HTTP requests and API endpoints
├── service/ # Contains business logic
├── repository/ # Manages database operations
├── entity/ # Defines database models/entities
├── dto/ # Data Transfer Objects for API communication

---

##  Installation

###  Prerequisites

- Java 17+
- Maven
- PostgreSQL or MySQL

###  Backend Setup

1. **Clone the repository:**

git clone https://github.com/SiMail03/smart-attendance.git
cd smart-attendance/Backend

2. **Configure the database:**
   - Update `application.properties` with your database credentials.

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/smart_attendance
    username: your_username
    password: your_password

3.  **Set Up JWT Secret Key:**

export JWT_SECRET=your_secret_key


4.  **Run the application:**

mvn spring-boot:run





