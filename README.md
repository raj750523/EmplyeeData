# EmplyeeData


Employee Records API
A Spring Boot REST API for managing employee records with CRUD operations, soft delete, search, pagination, and sorting.

🚀 Features
✅ Create, Read, Update, Delete (CRUD) employees
✅ Soft Delete (Mark as deleted instead of permanent removal)
✅ Search Employees by name (case-insensitive)
✅ Pagination & Sorting for employee retrieval
✅ Validation for input fields (name, age, etc.)
✅ Exception Handling for better error responses

🛠 Tech Stack
Spring Boot (REST API)
Spring Data JPA (ORM)
Hibernate (JPA Implementation)
MySQL (or any relational database)
Lombok (to reduce boilerplate code)
Postman (for API testing)
⚙️ Setup Instructions
1️⃣ Clone the Repository
sh
Copy
Edit
git clone https://github.com/your-username/employee-records.git
cd employee-records
2️⃣ Configure the Database
Update application.properties in src/main/resources/:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Build and Run
sh
Copy
Edit
mvn clean install
mvn spring-boot:run
📌 API Endpoints
1️⃣ Create Employee
URL: POST /api/employees
Request Body:
json
Copy
Edit
{
  "name": "John Doe",
  "age": 30,
  "department": "IT"
}
Response: 201 Created
json
Copy
Edit
{
  "employeeId": 1,
  "name": "John Doe",
  "age": 30,
  "department": "IT",
  "deleted": false
}
2️⃣ Get Employee by ID
URL: GET /api/employees/{id}
Example: GET /api/employees/1
Response:
json
Copy
Edit
{
  "employeeId": 1,
  "name": "John Doe",
  "age": 30,
  "department": "IT",
  "deleted": false
}
3️⃣ Update Employee
URL: PUT /api/employees/{id}
Request Body:
json
Copy
Edit
{
  "name": "Jane Doe",
  "age": 32,
  "department": "HR"
}
Response: 200 OK
json
Copy
Edit
{
  "employeeId": 1,
  "name": "Jane Doe",
  "age": 32,
  "department": "HR",
  "deleted": false
}
4️⃣ Soft Delete Employee
URL: DELETE /api/employees/{id}
Example: DELETE /api/employees/1
Response: 200 OK
json
Copy
Edit
{
  "message": "Employee with ID 1 was successfully deleted."
}
5️⃣ Get All Employees (Search, Pagination, Sorting)
URL: GET /api/employees
Query Parameters:
search=John (Optional - Filter employees by name)
page=0 (Optional - Default: 0)
size=10 (Optional - Default: 10)
sort=name,asc (Optional - Default: name,asc)
Example:
sh
Copy
Edit
GET /api/employees?search=John&page=0&size=5&sort=name,asc
Response:
json
Copy
Edit
[
  {
    "employeeId": 1,
    "name": "John Doe",
    "age": 30,
    "department": "IT",
    "deleted": false
  }
]
