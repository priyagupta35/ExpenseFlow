# 💰 ExpenseFlow - Personal Finance Management REST API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-blue?style=for-the-badge&logo=springsecurity)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Neon_Cloud-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker)
![Render](https://img.shields.io/badge/Render-Deployed-46E3B7?style=for-the-badge&logo=render)

> A production-ready, containerized RESTful API for personal finance tracking, budget categorization, and monthly analytics. Built with **Spring Boot 3**, **Spring Security (JWT)**, and cloud-hosted on **Render** with a **Neon PostgreSQL** database.

🔗 **Live Deployment:** [https://expenseflow-1-n72u.onrender.com](https://expenseflow-1-n72u.onrender.com)  
📖 **Interactive Swagger UI:** [https://expenseflow-1-n72u.onrender.com/swagger-ui/index.html](https://expenseflow-1-n72u.onrender.com/swagger-ui/index.html)

---

## 🌟 Key Features

- **🔐 Stateless Security & Auth**: User registration & login with BCrypt password hashing and JWT (JSON Web Tokens) verification.
- **🏷️ Custom Category Management**: Create, list, and delete custom income and expense categories per user.
- **💸 Transaction Management**: Log, view, and filter income and expense transactions by date, month, or transaction type.
- **📊 Real-Time Financial Summaries**:
  - Overall balance calculation (Total Income vs Total Expenses).
  - Monthly financial breakdown.
  - Category-wise spending aggregations.
- **🛡️ Global Exception Handling**: Centralized error responses for bad requests, validation errors, resource not found, and unauthorized access.
- **🐳 Containerized & Cloud-Ready**: Fully Dockerized multi-stage build running on Render cloud.

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.3.5 (Spring MVC, Spring Data JPA) |
| **Security** | Spring Security 6, JJWT 0.11.5 |
| **Database** | PostgreSQL (Neon Serverless Cloud) |
| **Connection Pooling** | HikariCP |
| **API Documentation** | SpringDoc OpenAPI 3 / Swagger UI |
| **DevOps & Deployment** | Docker (Multi-stage build), Render |
| **Build Tool** | Apache Maven |

---

## 🚀 Live API Endpoints

### 1. Public Endpoints
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/` | API Status & health check overview |
| `GET` | `/swagger-ui/index.html` | Interactive Swagger API documentation |
| `POST` | `/api/auth/register` | Register a new user account |
| `POST` | `/api/auth/login` | Authenticate user & receive JWT token |

### 2. Category Endpoints (Protected - Requires JWT)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/categories` | Retrieve all categories for logged-in user |
| `POST` | `/api/categories` | Create a new category (`INCOME` / `EXPENSE`) |
| `DELETE` | `/api/categories/{id}` | Delete a category owned by user |

### 3. Transaction Endpoints (Protected - Requires JWT)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/transactions` | Get all transactions of logged-in user |
| `GET` | `/api/transactions?type={INCOME\|EXPENSE}` | Filter transactions by type |
| `GET` | `/api/transactions?year={yyyy}&month={mm}` | Filter transactions by year & month |
| `POST` | `/api/transactions` | Add a new transaction |
| `DELETE` | `/api/transactions/{id}` | Delete a transaction owned by user |

### 4. Summary & Analytics (Protected - Requires JWT)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/summary/balance` | Get total income, total expenses & net balance |
| `GET` | `/api/summary/monthly?year={yyyy}&month={mm}` | Get monthly income and expense summary |
| `GET` | `/api/summary/category-wise` | Aggregated breakdown by category |

---

## 📋 Sample API Payloads

### User Registration (`POST /api/auth/register`)
```json
{
  "username": "priya",
  "email": "priya@example.com",
  "password": "password123"
}
User Login (POST /api/auth/login)
json


{
  "email": "priya@example.com",
  "password": "password123"
}


## 🛠️ Local Setup & Installation

### Prerequisites

Make sure you have the following installed:

* **JDK 17 or higher**
* **Git**
* **Maven** (optional — Maven Wrapper is included)
* **Docker** (optional, only if you want to run the application in a container)
* A **PostgreSQL database** or **Neon PostgreSQL** database

### 1. Clone the Repository

```bash
git clone https://github.com/priyagupta35/ExpenseFlow.git
cd ExpenseFlow/ExpenseFlow
```

### 2. Configure Environment Variables

ExpenseFlow does not require database credentials or the JWT secret to be stored directly in `application.properties`.

The application reads the following environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
```

Your `src/main/resources/application.properties` should contain:

```properties
spring.application.name=expense-tracker

server.port=${PORT:8080}

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.open-in-view=false

jwt.secret=${JWT_SECRET}
jwt.expiration=86400000
```

**Do not commit database passwords or JWT secrets to GitHub.**

#### Windows CMD

Set the variables for the current terminal session:

```cmd
set DB_URL=jdbc:postgresql://<your-neon-host>/neondb?sslmode=require
set DB_USERNAME=<your-username>
set DB_PASSWORD=<your-password>
set JWT_SECRET=<your-secret-key>
```

Then start the application:

```cmd
mvnw.cmd spring-boot:run
```

#### Windows PowerShell

```powershell
$env:DB_URL="jdbc:postgresql://<your-neon-host>/neondb?sslmode=require"
$env:DB_USERNAME="<your-username>"
$env:DB_PASSWORD="<your-password>"
$env:JWT_SECRET="<your-secret-key>"
```

Then:

```powershell
.\mvnw.cmd spring-boot:run
```

#### Linux / macOS

```bash
export DB_URL="jdbc:postgresql://<your-neon-host>/neondb?sslmode=require"
export DB_USERNAME="<your-username>"
export DB_PASSWORD="<your-password>"
export JWT_SECRET="<your-secret-key>"
```

Then:

```bash
./mvnw spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

### 3. Run with Docker

To build the Docker image from the project directory:

```bash
docker build -t expenseflow .
```

Run the container by supplying the required environment variables:

```bash
docker run --name expenseflow-api -p 8080:8080 ^
  -e DB_URL="<your-neon-jdbc-url>" ^
  -e DB_USERNAME="<your-neon-username>" ^
  -e DB_PASSWORD="<your-neon-password>" ^
  -e JWT_SECRET="<your-jwt-secret>" ^
  expenseflow
```

For Linux/macOS, use:

```bash
docker run --name expenseflow-api -p 8080:8080 \
  -e DB_URL="<your-neon-jdbc-url>" \
  -e DB_USERNAME="<your-neon-username>" \
  -e DB_PASSWORD="<your-neon-password>" \
  -e JWT_SECRET="<your-jwt-secret>" \
  expenseflow
```

The API will then be available at:

```text
http://localhost:8080
```
