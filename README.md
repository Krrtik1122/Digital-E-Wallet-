💳 Digital E-Wallet Backend

Main Application Class: TransactionSafeWalletBackendApplication
Base Package: com.krrtk.demo
Working Folder: transaction

A secure banking-style wallet backend built using Spring Boot, Spring Security (JWT) and PostgreSQL implementing real transaction lifecycle management with enum-based status handling.

⚠ Current Status: Runs on supabase PostgreSQL – cloud deployed

🧱 Actual Project Structure
transaction/src/main/java
 └── com.krrtk.demo
     ├── controller
     │   ├── TransactionController.java
     │   ├── UserController.java
     ├── service
     │   ├── BankingService.java
     ├── repository
     │   ├── TransactionRepository.java
     │   ├── UserRepository.java
     ├── entity
     │   ├── User.java
     │   ├── Transaction.java
     ├──enums
     │   ├── TransactionStatus.java (ENUM)
     ├── config
     │   ├── SecurityBeansConfig.java
     │   ├── SecurityConfig.java
     ├── security
     │   ├── JwtFilter.java

🧾 Domain Model
1. Transaction Entity

amount

sender

receiver

timestamp

status (TransactionStatus)

2. User Entity

userName

password

balance

roles

3. TransactionStatus Enum

PENDING – initial state

COMPLETED – successful transfer

FAILED – insufficient balance / error

⚙ Config Module
SecurityBeansConfig.java

Provides reusable security beans:

BCryptPasswordEncoder

AuthenticationManager

UserDetailsService

Common security utilities

SecurityConfig.java

Defines SecurityFilterChain

Public vs protected endpoints

Registers JwtFilter

Stateless session policy

CORS / CSRF rules

🚀 Features
Banking Operations

Add money to wallet

Transfer between users

View balance

Transaction history

Automatic status update

PENDING → COMPLETED / FAILED

Security

JWT based authentication

BCrypt password encryption

Bean-based security configuration

Stateless session

Protected REST APIs

Reliability

Atomic money transfer

Validation checks

Proper exception handling

Enum driven status management

🛠 Tech Stack

Java

Spring Boot

Spring Security

JWT

PostgreSQL

JPA / Hibernate

Maven

▶ How to Run (Local Setup)
1. Clone Repository
git clone <repo-url>

2. Move to Main Folder
cd transaction

3. PostgreSQL Setup
CREATE DATABASE walletdb;


application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/wallet_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Run Application
mvn spring-boot:run


OR run:

com.krrtk.demo.TransactionSafeWalletBackendApplication

📌 API Overview
UserController

POST /user/register

POST /user/login

GET /user/balance

TransactionController

POST /transaction/add

POST /transaction/transfer


🔁 Transaction Lifecycle

Request received → PENDING

Balance validation

Debit + Credit operations

On success → COMPLETED

On failure → FAILED

🔐 Security Flow

User logs in → JWT generated

JwtFilter intercepts requests

SecurityConfig validates route

Beans from SecurityBeansConfig used

Access granted to controllers

🧪 Sample Request
Register User
{
  "userName": "kartik",
  "password": "1234"
}

Add Money
{
  "amount": 1000
}

🚧 Current Status

✅ Entities & Enums

✅ BankingService

✅ Transaction lifecycle

✅ JWT Security

🔮 Future Enhancements

Swagger documentation

Email alerts

Redis caching

JUnit & Mockito tests

Cloud deployment

👤 Developer

Kartik
Java Spring Boot Developer
Project: Digital E-Wallet
