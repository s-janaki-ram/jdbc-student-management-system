# JDBC Student Management System

This is a simple **Java JDBC Console Application** that performs CRUD operations on a MySQL database.

## Features

* Insert Student
* Update Student Course
* Delete Student
* Display All Students
* Search Student by ID

## Technologies Used

* Java
* JDBC
* MySQL

## Database Table

```sql
CREATE TABLE student(
sid INT PRIMARY KEY,
name VARCHAR(50),
course VARCHAR(50)
);
```

## How to Run

1. Create database `jdbc` in MySQL
2. Create the `student` table
3. Update database username and password in the code
4. Run the program

## Example Menu

```
1. Insert Record
2. Update Record
3. Delete Record
4. Display Records
5. Find Record
6. Exit
```

This project demonstrates the use of **PreparedStatement, ResultSet, and JDBC CRUD operations**.
