# 💰 Expense Tracker Backend

A secure RESTful Expense Tracker backend built using **Spring Boot**. The application provides user authentication with **JWT**, password encryption using **BCrypt**, and a layered architecture for building scalable REST APIs.

---

## 🚀 Features

### Authentication
- User Registration
- User Login
- JWT Authentication
- BCrypt Password Encryption
- Protected REST APIs

### Security
- Spring Security
- JWT Token Validation
- Stateless Authentication
- Role-based authentication ready

### Backend
- RESTful APIs
- Spring Data JPA
- Hibernate ORM
- MySQL Database
- DTO Pattern
- Global Exception Handling
- Request Validation

---

## 🛠 Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.16 |
| Spring Security | 6.x |
| Spring Data JPA | Latest |
| Hibernate | 6.x |
| MySQL | 8.x |
| JWT (JJWT) | Latest |
| Maven | 3.x |

---

## 📁 Project Structure

```
src
 ├── main
 │   ├── java
 │   │   └── com.varun.expensetracker
 │   │        ├── config
 │   │        ├── controller
 │   │        ├── dto
 │   │        ├── entity
 │   │        ├── exception
 │   │        ├── filter
 │   │        ├── repository
 │   │        ├── service
 │   │        └── ExpenseTrackerApplication.java
 │   │
 │   └── resources
 │        └── application.properties
 │
 └── test
```

---

## 🔐 Authentication APIs

### Register User

```
POST /api/users/register
```

### Login User

```
POST /api/users/login
```

Returns:

```json
{
    "token": "JWT_TOKEN",
    "message": "Login Successful"
}
```

---

## 🔒 Protected API Example

```
GET /api/test
```

Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

## ⚙️ Configuration

Create a MySQL database:

```sql
CREATE DATABASE expense_tracker;
```

Create an `application.properties` file based on `application-example.properties` and configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000
```

---

## ▶️ Run the Project

Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/expense-tracker.git
```

Navigate to the project:

```bash
cd expense-tracker
```

Run the application:

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

## 📌 Current Status

- ✅ User Registration
- ✅ User Login
- ✅ JWT Authentication
- ✅ Spring Security
- ✅ Password Encryption
- ✅ Global Exception Handling
- ✅ Request Validation

### 🚧 Upcoming Features

- Expense CRUD APIs
- Category-wise Expenses
- Monthly Reports
- Dashboard APIs
- Pagination & Sorting
- Search & Filters
- Swagger/OpenAPI Documentation

---

## 👨‍💻 Author

**Boddu Madhu Veera Satya Varun**

GitHub: https://github.com/YOUR_USERNAME

LinkedIn: Add your LinkedIn profile here.

---

## 📜 License

This project is created for learning and portfolio purposes.