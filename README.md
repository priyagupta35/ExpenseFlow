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



Local Setup & Installation
Prerequisites
JDK 17 or higher
Git
PostgreSQL instance (or Neon database connection string)
1. Clone the repository
git clone https://github.com/priyagupta35/ExpenseFlow.git
cd ExpenseFlow/ExpenseFlow
2. Configure Environment Variables / application.properties
Set up your database credentials and JWT secret in src/main/resources/application.properties or as system environment variables:
properties:-
spring.datasource.url=jdbc:postgresql://<your-db-host>/<db-name>?sslmode=require
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
jwt.secret=<your-256-bit-secret-key>
jwt.expiration=86400000
3. Run the Application
On Windows:

cmd
mvnw.cmd spring-boot:run
On Linux / macOS:
bash
./mvnw spring-boot:run
The application will start at: http://localhost:8080

🐳 Docker Deployment
To build and run the Docker container locally:

# Build the Docker image
docker build -t expenseflow:latest -f ExpenseFlow/Dockerfile .
# Run the container on port 8080
docker run -p 8080:8080 expenseflow:latest


GitHub: @priyagupta35
Project: ExpenseFlow Repository


