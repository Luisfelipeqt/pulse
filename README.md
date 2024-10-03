# Pulse Application

## Overview

This project is a Spring Boot application that provides APIs for user registration, login, and product management. It uses PostgreSQL as the database and is containerized using Docker.

## Technologies Used

- Java
- Spring Boot
- Maven
- PostgreSQL
- Docker
- Swagger for API documentation

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Docker
- Docker Compose

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Luisfelipeqt/pulse
cd pulse
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

#### Using Docker Compose

1. Start the PostgreSQL database:

    ```bash
    docker-compose up -d
    ```

2. Run the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

#### Without Docker

1. Start a PostgreSQL instance and create a database named `pulse`.
2. Login to the PostgreSQL instance and create a user with the following credentials:

    - Username: `postgres`
    - Password: `postgres`
    - Database: `pulse`

3. Run the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

## API Documentation

The API documentation is available at `/swagger-ui.html` once the application is running.

## Database Migrations

The project uses SQL migration files to set up the database schema. The migration files are located in the `src/main/resources/db/migration` directory.

### Migration Files

- `V0001__Create_Product_Table.sql`: Creates the `products` table with constraints and indexes.
- `V0002__Populate_Product_Table.sql`: Populates the `products` table with initial data.
- `V0003__Create_Users_Table.sql`: Creates the `users` table with necessary fields and constraints.

