# Library Book Management System

A production-ready RESTful web service built with **Java Spring Boot**, **Spring Data JPA**, and a **Relational Database** (H2 / MySQL) to manage a library's collection of books.

---

## 📋 Features & Requirements

The system implements all requirements specified in the case study:

1. **Addition (`POST`)**: Add a new book to the library with full JSON validation and unique ISBN checks.
2. **Retrieval (`GET`)**: Retrieve the entire collection of books or a specific book by its primary ID.
3. **Search (`GET`)**: Flexible search queries supporting title, author, ISBN, or a unified keyword across all fields.
4. **Deletion (`DELETE`)**: Remove a book record by its ID with validation.
5. **Update (`PUT`)**: Modify an existing book record's details with collision checks.
6. **Relational Database**: Entity-Relational mapping using JPA/Hibernate with embedded H2 or external MySQL.
7. **Postman Testing**: Ready-to-import Postman Collection file with pre-configured requests and sample bodies.
8. **Robust Error Handling**: Centralized `@RestControllerAdvice` returning structured error payloads for 400 Bad Request, 404 Not Found, and 409 Conflict.

---

## 🏗️ System Architecture

The application is structured into clean separation-of-concerns layers:

```
[ Client: Postman / Browser / Frontend ]
                   │
                   ▼ (HTTP JSON Requests)
┌──────────────────────────────────────────────────────────┐
│ Controller Layer                                         │
│ - BookController (/api/books)                            │
│ - GlobalExceptionHandler (@RestControllerAdvice)        │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│ Service Layer                                            │
│ - BookService (Interface)                                │
│ - BookServiceImpl (@Transactional Business Logic)       │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│ Repository Layer                                         │
│ - BookRepository (Spring Data JPA / JPQL Queries)       │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│ Relational Database (H2 In-Memory / MySQL)               │
│ - books Table (Primary Key: ID, Unique Constraint: ISBN) │
└──────────────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
spring/
├── pom.xml                                               # Maven dependencies & build config
├── mvnw / mvnw.cmd                                       # Maven Wrapper scripts
├── postman/
│   └── Library_Book_Management_System.postman_collection.json # Exported Postman collection
├── src/
│   ├── main/
│   │   ├── java/com/kiet/library/
│   │   │   ├── LibraryManagementApplication.java         # Spring Boot main class
│   │   │   ├── config/
│   │   │   │   └── DataInitializer.java                 # Preloads sample books on startup
│   │   │   ├── controller/
│   │   │   │   └── BookController.java                  # REST endpoints (POST, GET, PUT, DELETE)
│   │   │   ├── entity/
│   │   │   │   └── Book.java                            # JPA Relational Entity
│   │   │   ├── exception/
│   │   │   │   ├── DuplicateResourceException.java      # 409 Conflict exception
│   │   │   │   ├── ErrorResponse.java                   # Uniform JSON error model
│   │   │   │   ├── GlobalExceptionHandler.java          # Centralized exception handler
│   │   │   │   └── ResourceNotFoundException.java       # 404 Not Found exception
│   │   │   ├── repository/
│   │   │   │   └── BookRepository.java                  # Spring Data JPA repository
│   │   │   └── service/
│   │   │       ├── BookService.java                     # Service interface
│   │   │       └── impl/BookServiceImpl.java            # Service implementation
│   │   └── resources/
│   │       └── application.properties                   # App & Database configuration
│   └── test/
│       └── java/com/kiet/library/
│           ├── LibraryManagementApplicationTests.java   # Context load test
│           └── controller/
│               └── BookControllerIntegrationTest.java   # End-to-end integration test suite
```

---

## 🗄️ Relational Database Schema (`books` table)

| Column Name | SQL Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | `BIGINT` | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for each book |
| `title` | `VARCHAR(200)` | NOT NULL | Title of the book |
| `author` | `VARCHAR(100)` | NOT NULL | Author of the book |
| `isbn` | `VARCHAR(255)` | NOT NULL, UNIQUE | International Standard Book Number |
| `genre` | `VARCHAR(255)` | NULLABLE | Category or genre |
| `publication_year` | `INT` | NULLABLE | Year of publication |
| `price` | `DOUBLE` | NOT NULL, CHECK (>= 0) | Price of the book |
| `total_copies` | `INT` | NOT NULL, CHECK (>= 1) | Total copies owned by the library |
| `available_copies` | `INT` | NOT NULL, CHECK (>= 0) | Copies currently available to borrow |

---

## 🚀 How to Run the Application

### Option A: Using Maven Wrapper (Recommended)

In the `spring` project directory, run:

```powershell
.\mvnw.cmd spring-boot:run
```

### Option B: Using Packaged Executable JAR

```powershell
java -jar target\library-management-0.0.1-SNAPSHOT.jar
```

The application will start on **`http://localhost:8080`**.

---

## 🌐 H2 Database Web Console

An interactive web console is built-in to inspect relational database tables directly:

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:librarydb`
- **User Name**: `sa`
- **Password**: *(leave empty)*

Click **Connect** to run SQL queries like `SELECT * FROM books;`.

---

## 📡 REST API Reference

Base URL: `http://localhost:8080/api/books`

### 1. Addition: Add a New Book

- **Method**: `POST`
- **URL**: `/api/books`
- **Headers**: `Content-Type: application/json`
- **Request Body**:
```json
{
  "title": "Designing Data-Intensive Applications",
  "author": "Martin Kleppmann",
  "isbn": "978-1449373320",
  "genre": "Distributed Systems",
  "publicationYear": 2017,
  "price": 59.99,
  "totalCopies": 10,
  "availableCopies": 10
}
```
- **Response**: `201 Created`
- **Header**: `Location: /api/books/6`
- **Response Body**:
```json
{
  "id": 6,
  "title": "Designing Data-Intensive Applications",
  "author": "Martin Kleppmann",
  "isbn": "978-1449373320",
  "genre": "Distributed Systems",
  "publicationYear": 2017,
  "price": 59.99,
  "totalCopies": 10,
  "availableCopies": 10
}
```

---

### 2. Retrieval: Get All Books or Specific Book

#### 2a. Get All Books
- **Method**: `GET`
- **URL**: `/api/books`
- **Response**: `200 OK`
```json
[
  {
    "id": 1,
    "title": "Clean Code: A Handbook of Agile Software Craftsmanship",
    "author": "Robert C. Martin",
    "isbn": "978-0132350884",
    "genre": "Software Engineering",
    "publicationYear": 2008,
    "price": 42.99,
    "totalCopies": 10,
    "availableCopies": 8
  },
  {
    "id": 2,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "genre": "Programming",
    "publicationYear": 2018,
    "price": 47.50,
    "totalCopies": 15,
    "availableCopies": 12
  }
]
```

#### 2b. Get Book by ID
- **Method**: `GET`
- **URL**: `/api/books/{id}` (e.g. `/api/books/1`)
- **Response**: `200 OK`
```json
{
  "id": 1,
  "title": "Clean Code: A Handbook of Agile Software Craftsmanship",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "genre": "Software Engineering",
  "publicationYear": 2008,
  "price": 42.99,
  "totalCopies": 10,
  "availableCopies": 8
}
```
- **Error Response (If ID does not exist)**: `404 Not Found`
```json
{
  "timestamp": "2026-10-15T15:46:43",
  "status": 404,
  "error": "Not Found",
  "message": "Book not found with ID: 9999",
  "path": "/api/books/9999",
  "details": null
}
```

---

### 3. Search: Search Books by Title, Author, or ISBN

- **Method**: `GET`
- **URL**: `/api/books/search`

#### Supported Query Parameters:

| Parameter | Example | Description |
| :--- | :--- | :--- |
| `title` | `/api/books/search?title=Clean` | Case-insensitive title match |
| `author` | `/api/books/search?author=Bloch` | Case-insensitive author match |
| `isbn` | `/api/books/search?isbn=978-0134685991` | Search by ISBN |
| `keyword` | `/api/books/search?keyword=Algorithms` | Multi-field search across title, author, and ISBN |

- **Sample Response**: `200 OK`
```json
[
  {
    "id": 2,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "genre": "Programming",
    "publicationYear": 2018,
    "price": 47.50,
    "totalCopies": 15,
    "availableCopies": 12
  }
]
```

---

### 4. Update: Update an Existing Book

- **Method**: `PUT`
- **URL**: `/api/books/{id}` (e.g. `/api/books/1`)
- **Headers**: `Content-Type: application/json`
- **Request Body**:
```json
{
  "title": "Clean Code: A Handbook of Agile Software Craftsmanship (Updated Edition)",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "genre": "Software Craftsmanship",
  "publicationYear": 2008,
  "price": 49.99,
  "totalCopies": 15,
  "availableCopies": 11
}
```
- **Response**: `200 OK` with updated book JSON.

---

### 5. Deletion: Delete a Book by ID

- **Method**: `DELETE`
- **URL**: `/api/books/{id}` (e.g. `/api/books/1`)
- **Response**: `200 OK`
```json
{
  "success": true,
  "message": "Book with ID 1 has been successfully deleted.",
  "deletedId": 1
}
```

---

## 📮 How to Test Using Postman

1. Open **Postman**.
2. Click **Import** (top left).
3. Select or drag & drop the collection file:
   `d:\VS Code Projects\KIET Ghaziabad\java_kiet\spring\postman\Library_Book_Management_System.postman_collection.json`
4. The collection **"Library Book Management System API"** will appear with pre-configured requests:
   - `1. Addition (Add New Book)`
   - `2a. Retrieval (Get All Books)`
   - `2b. Retrieval (Get Book by ID)`
   - `3a. Search (Search by Title)`
   - `3b. Search (Search by Author)`
   - `3c. Search (Search by ISBN)`
   - `3d. Search (Search by Keyword across fields)`
   - `4. Update (Update Book Details)`
   - `5. Deletion (Delete Book by ID)`
   - `6. Validation Test (Missing Required Fields - 400 Bad Request)`
   - `7. Error Test (Resource Not Found - 404 Not Found)`
5. Run the requests one by one to verify client-server communication!

---

## 🧪 Running Automated Tests

To execute the test suite (15 tests covering all CRUD operations, validations, duplicate checks, and search algorithms):

```powershell
.\mvnw.cmd test
```

Expected output:
```
[INFO] Results:
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 🐬 Switching to MySQL (Optional)

To switch from the embedded H2 relational database to an external MySQL database:

1. Open `src/main/resources/application.properties`.
2. Comment out the H2 settings and uncomment the MySQL configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```
3. Add MySQL connector to `pom.xml`:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```
