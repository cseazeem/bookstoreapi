# Bookstore REST API

A simple RESTful API built with Spring Boot to manage a bookstore. It provides basic CRUD (Create, Read, Update, Delete) operations for books, using an in-memory H2 database. The project is designed to be standalone, lightweight, and includes proper exception handling and unit tests.

## Features
- **CRUD Operations:**
  - Create a new book (`POST /books`)
  - Retrieve a single book by ID (`GET /books/{id}`)
  - Retrieve all books (`GET /books`)
  - Update an existing book (`PUT /books/{id}`)
  - Delete a book (`DELETE /books/{id}`)
- In-memory H2 database for persistence
- Input validation for book data
- Proper exception handling with meaningful error responses
- Unit tests to verify API functionality

## Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Database:** H2 (in-memory)
- **Build Tool:** Maven
- **Dependencies:**
  - Spring Web
  - Spring Data JPA
  - H2 Database
  - Spring Boot DevTools
  - Spring Boot Test

## Prerequisites
- Java 17 or higher
- Maven 3.x
- Git (optional, for cloning)

## Setup and Running
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/cseazeem/bookstoreapi.git
   cd bookstore-api


API Endpoints
Method	Endpoint	Description	Request Body (JSON) Example
POST	/books	Create a new book	{"title": "Book1", "author": "Author1", "price": 10.0}
GET	/books/{id}	Get a book by ID	-
GET	/books	Get all books	-
PUT	/books/{id}	Update a book by ID	{"title": "Updated", "author": "Author2", "price": 15.0}
DELETE	/books/{id}	Delete a book by ID	-

Example Responses
Success (GET /books/1):
{
  "id": 1,
  "title": "Book1",
  "author": "Author1",
  "price": 10.0
}
Status: 200 OK

Not Found (GET /books/999):
{
  "error": "Not Found",
  "message": "Book with ID 999 not found"
}
Status: 404 Not Found

Validation Error (POST /books):
{
  "title": "Title is mandatory",
  "price": "Price must be positive"
}
Status: 400 Bad Request
Running Tests
   
