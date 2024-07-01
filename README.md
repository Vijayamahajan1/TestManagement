# Spring Boot MCQ Question Management System
This project is a Spring Boot application for managing Multiple Choice Questions (MCQs). It provides CRUD operations via RESTful endpoints for managing MCQs in a database.
### Technologies Used
- Java 21
- Spring Boot 3 (latest version)
- PostgreSQL
- JUnit 5 (for testing)
 
## Project Structure
 
The project is structured as follows:
- Controller: Handles incoming HTTP requests, processes them, and returns a response.
- Service: Contains business logic.
- Model: Defines the structure of MCQ questions.
- Repository: Interfaces for database operations (not shown explicitly in provided code).
 
### Setup Instructions
 
To run the project locally, follow these steps:
 
1. Set Up PostgreSQL Database
   - Create a PostgreSQL database named `TestManagementDB`.
   - Update the database configuration in `application.properties` file.
 
2. Run the Application
   - Open the project in your preferred IDE (e.g., IntelliJ IDEA, VS Code).
   - Run the `TestManagementApplication.java` class as a Java application.
 
3. Testing the API
   - Use Postman or any API testing tool to interact with the endpoints.
   - Import the provided Postman API collection to quickly test each endpoint.
### API Endpoints
 
#### Create MCQ Question
- Endpoint: `POST /TestManagement`
- Creates a new MCQ question in the database.
 
#### Update MCQ Question
- Endpoint: `PUT /TestManagement/update`
- Updates an existing MCQ question in the database.
 
#### Get All MCQ Questions
- Endpoint: `GET /TestManagement`
- Retrieves all MCQ questions stored in the database.
 
#### Get MCQ Question by ID
- Endpoint: `GET /TestManagement/{id}`
- Retrieves a specific MCQ question by its unique ID.
 
#### Delete MCQ Question
- Endpoint: `DELETE /TestManagement/{id}`
- Deletes a specific MCQ question by its unique ID.

# Category and Subcategory Operations

## Category CRUD Operations

### Create Category

**POST** `http://localhost:8080/Category`

Create a new category using JSON payload in the request body.

### Read All Categories

**GET** `http://localhost:8080/Category`

Retrieve all categories stored in the database.

### Update Category

**PUT** `http://localhost:8080/Category`

Update an existing category identified by categoryId using JSON payload in the request body.

### Delete Category

**DELETE** `http://localhost:8080/Category/{id}`

Delete an existing category identified by categoryId.

## Subcategory CRUD Operations

### Create Subcategory

**POST** `http://localhost:8080/SubCategory`

Create a new subcategory using JSON payload in the request body.

### Read All Subcategories

**GET** `http://localhost:8080/SubCategory`

Retrieve all subcategories stored in the database.

### Update Subcategory

**PUT** `http://localhost:8080/SubCategory`

Update an existing subcategory identified by subcategoryId using JSON payload in the request body.

### Delete Subcategory

**DELETE** `http://localhost:8080/SubCategory/{id}`

Delete an existing subcategory identified by subcategoryId.
 
 
### Contribution
Feel free to fork this repository, propose changes via pull requests, or report issues.
