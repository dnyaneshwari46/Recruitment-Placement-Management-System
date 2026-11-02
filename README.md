# Recruitment & Placement Management System

A full-stack Recruitment and Placement Management System developed using Java, MySQL, HTML, CSS and JavaScript.

## Technologies Used

* **Frontend:** HTML, CSS, JavaScript
* **Backend:** Java 17
* **Database:** MySQL 8
* **Database Connectivity:** JDBC
* **Server:** Java HTTP Server
* **IDE:** Visual Studio Code

## Features

1. Candidate Management
2. Company Management
3. Job Management
4. Application Management
5. Interview Management
6. Offer Management
7. Placement Management
8. Interview Feedback Management
9. Eligibility Checking
10. Reports Management
11. Login and Dashboard

## Project Structure

```
RecruitmentPlacementSystem/
│
├── database/
│   └── rpms_db_backup.sql
│
├── frontend/
│   ├── index.html
│   ├── dashboard.html
│   ├── candidates.html
│   ├── companies.html
│   ├── jobs.html
│   ├── applications.html
│   ├── interviews.html
│   ├── offers.html
│   ├── placements.html
│   ├── feedback.html
│   ├── eligibility.html
│   └── reports.html
│
├── lib/
│   └── mysql-connector-j/
│
├── src/
│   ├── app/
│   ├── dao/
│   ├── exception/
│   ├── model/
│   ├── service/
│   └── util/
│
└── .gitignore
```

## Requirements

* JDK 17
* MySQL Server 8
* MySQL Connector/J
* Visual Studio Code (recommended)

## Database Setup

1. Open MySQL Workbench.
2. Connect to your MySQL Server.
3. Open the file `<span>database/rpms_db_backup.sql</span>`.
4. Execute the SQL script.
5. Verify that the `<span>rpms_db</span>` database and its tables have been created.

## Database Configuration

Configure the MySQL connection in `<span>DBConnection.java</span>`:

* URL: `<span>jdbc:mysql://localhost:3306/rpms_db</span>`
* Username: Your MySQL username
* Password: Your MySQL password

Do not commit private passwords to GitHub.

## Compile the Project

Open the project folder in VS Code and run:

```
javac --add-modules jdk.httpserver -cp "lib/mysql-connector-j-26.10.0/mysql-connector-j-26.10.0/*" -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

## Run the Project

```
java --add-modules jdk.httpserver -cp "out;lib/mysql-connector-j-26.10.0/mysql-connector-j-26.10.0/*" app.WebServer
```

Open your browser:

```
http://localhost:8080/index.html
```

## Architecture

Main → Service → DAO → JDBC → MySQL

The frontend communicates with the Java backend through HTTP APIs. Java uses JDBC to interact with the MySQL database.

## Author

Developed as a Java Full Stack Development Project.
