# 💰 Expense Tracker Backend

A secure, scalable, and RESTful Expense Tracker backend built using **Spring Boot**. This project demonstrates industry-standard backend development practices, including **JWT Authentication**, **Spring Security**, **Hibernate**, **JPA**, and **MySQL**.

> **Current Status:** Authentication Module ✅ | Expense Management Module 🚧

---

# 📖 Overview

The Expense Tracker Backend provides secure user authentication and authorization using JSON Web Tokens (JWT). It follows a layered architecture with Controllers, Services, Repositories, DTOs, and Global Exception Handling to build maintainable and scalable REST APIs.

The project is being developed as a portfolio project to demonstrate modern Java backend development practices.

---

# ✨ Features

## 🔐 Authentication

- User Registration
- User Login
- JWT Token Generation
- JWT Authentication Filter
- Stateless Authentication
- BCrypt Password Encryption
- Protected REST APIs

---

## 🛡️ Security

- Spring Security
- JWT Token Validation
- Password Hashing using BCrypt
- Authentication & Authorization
- Secure API Access

---

## ⚙️ Backend

- RESTful API Development
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- MySQL Database
- DTO Pattern
- Layered Architecture
- Global Exception Handling
- Request Validation

---

# 🛠️ Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.x |
| Spring Security | 6.x |
| Spring Data JPA | Latest |
| Hibernate | 6.x |
| MySQL | 8.x |
| JWT (JJWT) | Latest |
| Maven | 3.x |

---

# 📂 Project Structure

```
expense-tracker
│
├── src
│   ├── main
│   │
│   ├── java
│   │   └── com.varun.expensetracker
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── exception
│   │       ├── filter
│   │       ├── repository
│   │       ├── service
│   │       └── ExpenseTrackerApplication.java
│   │
│   └── resources
│       ├── application-example.properties
│       ├── static
│       └── templates
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# 🔐 Authentication APIs

## Register User

```
POST /api/users/register
```

### Request

```json
{
    "name":"Rahul",
    "email":"rahul@gmail.com",
    "password":"123456"
}
```

---

## Login User

```
POST /api/users/login
```

### Request

```json
{
    "email":"rahul@gmail.com",
    "password":"123456"
}
```

### Response

```json
{
    "token":"JWT_TOKEN",
    "message":"Login Successful"
}
```

---

# 🔒 Protected APIs

Example:

```
GET /api/test
```

### Header

```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# ⚙️ Configuration

## 1. Create MySQL Database

```sql
CREATE DATABASE expense_tracker;
```

---

## 2. Configure Application

Copy

```
application-example.properties
```

to

```
application.properties
```

Update the following values:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000
```

---

# ▶️ Running the Project

Clone the repository

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/expense-tracker.git
```

Navigate to the project

```bash
cd expense-tracker
```

Run using Maven Wrapper

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

# 🗄️ Database

Database Used

- MySQL 8.x

ORM

- Hibernate

Persistence

- Spring Data JPA

---

# 📌 Current Progress

## Completed

- User Registration
- User Login
- JWT Authentication
- Spring Security
- BCrypt Password Encryption
- Global Exception Handling
- Request Validation
- DTO Layer
- Repository Layer
- Service Layer

---

## 🚧 Upcoming Features

- Expense CRUD APIs
- Category Management
- Expense Filters
- Monthly Reports
- Dashboard APIs
- Pagination
- Sorting
- Search APIs
- Swagger/OpenAPI Documentation
- Docker Deployment

---

# 🚀 Future Improvements

- Role-Based Authorization
- Refresh Tokens
- Email Verification
- Password Reset
- Unit Testing
- Integration Testing
- Docker
- CI/CD Pipeline
- AWS Deployment

---

# 🤝 Contributing

Contributions, suggestions, and improvements are welcome.

1. Fork the repository
2. Create a new feature branch
3. Commit your changes
4. Push the branch
5. Open a Pull Request

---

# 👨‍💻 Author

**Boddu Madhu Veera Satya Varun**

Information Technology Student

GitHub:
https://github.com/bvarun18

LinkedIn:
(Add your LinkedIn profile URL here)

---

# 📄 License

This project is developed for educational purposes and portfolio demonstration.

Feel free to use this project as a reference for learning Spring Boot, Spring Security, JWT Authentication, Hibernate, and REST API development.

---

⭐ If you found this project useful, consider giving it a Star on GitHub!