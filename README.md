# 📊 Personal Finance Manager (REST API)

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
</p>

## 🎯 About The Project

This is an academic and portfolio project focused on developing a robust **RESTful API** for a personal finance management system. The main goal is to apply and solidify advanced backend development concepts using the Spring ecosystem, building a secure, scalable, and container-ready application.

The API allows users to manage their accounts, transaction categories (e.g., "Food," "Transport"), and register their financial transactions (income and expenses).

## ✨ Key Features

* **User Authentication:** (If applicable) Login and registration system with validation.
* **User CRUD:** Manage user profiles.
* **Category CRUD:** Create and manage transaction categories.
* **Account CRUD:** Manage different bank accounts or wallets.
* **Transaction Logging:** Endpoints to create, list, update, and delete income and expenses.
* **Data Validation:** Ensures data integrity at the API entry point.
* **Exception Handling:** Standardized error responses for the client.

## 🛠️ Tech Stack

* **Java 21+**
* **Spring Boot:**
    * Spring Web (For the REST API)
    * Spring Data JPA (For data persistence)
    * Spring Validation (For DTO validation)
    * (Optional: Spring Security for authentication)
* **MySQL:** Relational database for persistence.
* **JPA / Hibernate:** Object-Relational Mapping (ORM).
* **Docker & Docker Compose:** Containerization of the application and database.
* **Maven:** Dependency management.

## 🚀 Architectural Highlights

This project was built with a strong focus on industry best practices.

### 1. DTO (Request/Response) Pattern

To ensure decoupling between the API and the database entities, all data traffic is handled via Data Transfer Objects (DTOs).

The package structure clearly separates `Request` DTOs, which carry input validations, from `Response` DTOs, which securely format the output data (e.g., by omitting passwords).

### 2. Global Exception Handling

The API uses a `GlobalExceptionHandler` (`@ControllerAdvice`) to intercept and standardize all error responses. Instead of generic errors, the client receives a clean and informative JSON.

This is especially powerful for validations: the API can return a list of *all* invalid fields in a single request, improving the user and front-end developer experience.

## ⚙️ How to Run The Project

There are two ways to run the application:

### Option 1: With Docker (Recommended)

This is the simplest way to run the project, as it includes the MySQL database.

**Prerequisites:**
* [Git](https://git-scm.com/downloads)
* [Docker](https://www.docker.com/products/docker-desktop/)

```bash
# 1. Clone the repository
$git clone [https://github.com/bernardommecabo/personal-finance-manager.git$](https://github.com/bernardommecabo/personal-finance-manager.git$) cd personal-finance-manager

# 2. Start the containers (API + Database)
# Docker Compose will build the Spring image and start the MySQL container
$ docker-compose up -d --build

# 3. Access the application
The API will be available at http://localhost:8080
```

### Option 2: Locally (Without Docker)
* [Git](https://git-scm.com/downloads)
* [Java 21 (or higher)](https://www.oracle.com/java/technologies/downloads/)
* [Maven](https://maven.apache.org/download.cgi)
* A local [MySQL](https://dev.mysql.com/downloads/installer/) instance

```bash
# 1. Clone the repository
$git clone [https://github.com/bernardommecabo/personal-finance-manager.git$](https://github.com/bernardommecabo/personal-finance-manager.git$) cd personal-finance-manager

# 2. Configure the Database
# - Create a local database in MySQL (e.g., "personal_finance_db")
# - Configure your credentials in: src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/personal_finance_db
spring.datasource.username=YOUR_MYSQL_USER
spring.datasource.password=YOUR_MYSQL_PASSWORD

# 3. Run the application with Maven
$ mvn spring-boot:run

# 4. Access the application
The API will be available at http://localhost:8080
```

## 👨‍💻 Author

**Bernardo Guilherme Madruga Mecabô**

* [LinkedIn](https://www.linkedin.com/in/bernardomecabo/)
* [GitHub](https://github.com/bernardommecabo)