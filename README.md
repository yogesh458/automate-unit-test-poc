# Employee CRUD REST API

Spring Boot 3 REST API for managing employees with Java 21, Spring Data JPA, MySQL, Maven, Lombok, DTOs, validation, and global exception handling.

## Project Structure

```text
src/main/java/com/volane/employee_crud
├── EmployeeCrudApplication.java
├── controller
│   └── EmployeeController.java
├── dto
│   ├── ApiResponse.java
│   ├── EmployeeRequestDto.java
│   └── EmployeeResponseDto.java
├── entity
│   └── Employee.java
├── exception
│   ├── DuplicateResourceException.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository
│   └── EmployeeRepository.java
└── service
    ├── EmployeeService.java
    └── impl
        └── EmployeeServiceImpl.java
```

## MySQL Setup

Create the database before running the application:

```sql
CREATE DATABASE employee_crud_db;
```

The application uses this configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_crud_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

Update the username and password to match your local MySQL setup.

## SQL Table Structure

Hibernate can create the table automatically. If you want to create it manually, use:

```sql
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL,
    salary DOUBLE NOT NULL
);
```

## Run

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The API runs at:

```text
http://localhost:8080
```

## APIs

### Create Employee

`POST /api/employees`

Request:

```json
{
  "employeeId": "EMP001",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "department": "Engineering",
  "salary": 75000.0
}
```

Response `201 Created`:

```json
{
  "timestamp": "2026-06-23T22:30:00",
  "status": 201,
  "message": "Employee created successfully",
  "data": {
    "id": 1,
    "employeeId": "EMP001",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "department": "Engineering",
    "salary": 75000.0
  }
}
```

### Get Employee By Id

`GET /api/employees/1`

### Get All Employees

`GET /api/employees`

### Update Employee

`PUT /api/employees/1`

Request body is the same as create employee.

### Delete Employee

`DELETE /api/employees/1`

Response `200 OK`:

```json
{
  "timestamp": "2026-06-23T22:35:00",
  "status": 200,
  "message": "Employee deleted successfully",
  "data": null
}
```

## Error Responses

Validation error `400 Bad Request`:

```json
{
  "timestamp": "2026-06-23T22:40:00",
  "status": 400,
  "message": "Validation failed",
  "data": {
    "email": "Email must be valid",
    "salary": "Salary must be greater than 0"
  }
}
```

Not found error `404 Not Found`:

```json
{
  "timestamp": "2026-06-23T22:45:00",
  "status": 404,
  "message": "Employee not found with id: 99",
  "data": null
}
```

Duplicate error `409 Conflict`:

```json
{
  "timestamp": "2026-06-23T22:50:00",
  "status": 409,
  "message": "Email already exists: john.doe@example.com",
  "data": null
}
```

## Class And Annotation Explanation

`EmployeeCrudApplication` is the main bootstrapping class. `@SpringBootApplication` enables component scanning, auto-configuration, and configuration support.

`Employee` is the JPA entity mapped to the `employees` table. `@Entity` marks it as a database-backed model, `@Table` sets the table name, `@Id` marks the primary key, `@GeneratedValue(strategy = GenerationType.IDENTITY)` uses MySQL auto-increment, and `@Column` defines constraints such as uniqueness and nullability.

`EmployeeRequestDto` represents incoming create and update payloads. Jakarta Validation annotations such as `@NotBlank`, `@Email`, `@NotNull`, `@DecimalMin`, and `@Size` reject invalid requests before they reach the service layer.

`EmployeeResponseDto` represents the outgoing API payload. It keeps the REST response separate from the persistence entity.

`ApiResponse<T>` is a generic response wrapper containing `timestamp`, `status`, `message`, and `data`.

`EmployeeRepository` extends `JpaRepository<Employee, Long>`, which provides built-in CRUD methods such as `save`, `findById`, `findAll`, and `delete`. The `existsBy...` methods are derived queries used to prevent duplicate employee IDs and emails.

`EmployeeService` defines the business operations. `EmployeeServiceImpl` implements them, performs duplicate checks, maps between DTOs and entities, and throws custom exceptions when needed. `@Service` registers the class as a Spring bean, `@RequiredArgsConstructor` creates constructor injection for final dependencies, and `@Transactional` wraps database operations in transactions.

`EmployeeController` exposes REST endpoints. `@RestController` returns JSON responses, `@RequestMapping("/api/employees")` sets the base route, and `@PostMapping`, `@GetMapping`, `@PutMapping`, and `@DeleteMapping` define the CRUD endpoints. `@Valid` activates validation for request bodies.

`ResourceNotFoundException` is thrown when an employee id does not exist. `DuplicateResourceException` is thrown when `employeeId` or `email` already exists.

`GlobalExceptionHandler` uses `@ControllerAdvice` to centralize exception responses. `@ExceptionHandler` maps each exception type to the correct HTTP status and response body.

## Request Flow

1. The client sends a request to `EmployeeController`.
2. The controller validates the request DTO using Jakarta Validation.
3. The controller calls `EmployeeService`.
4. The service applies business rules, maps DTOs to entities, and calls `EmployeeRepository`.
5. The repository uses Spring Data JPA and Hibernate to query or update MySQL.
6. The service maps the saved or fetched entity back to a response DTO.
7. The controller returns a structured JSON response with the correct HTTP status.
