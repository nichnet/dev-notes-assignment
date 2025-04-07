# Spring Boot Assignment
This notes application is written in Java (Spring Boot 2) and uses MySQL. 
<p align="center">
  <img src="https://github.com/nichnet/dev-notes-assignment/blob/main/images/example.png?raw=true" style="width: 400px;"/>
</p>

## Prerequisites
Ensure the following are installed on your local machine:
Development:
- [OpenJDK 17](https://openjdk.org/projects/jdk/17/) (for both the backend/frontend apps)
- Maven (for building)
  evelopment and Production:
- Docker (for the dev database container and production setup, optionally install [Docker Desktop](https://www.docker.com/products/docker-desktop/))

## Running in Development Mode

### Database
To launch the containerized database, navigate to the `notes-database` folder and run the following command:
```bash
docker-compose up --build
```
In development mode, the database container will expose the mysql instance on port `3037`.

### Backend
To launch the backend you can run the application from the IDE **or** package it and run the built JAR (found in the target folder) with the `dev` profile.
Navigate to the `notes-backend` folder and run the following commands:
```bash
mvn clean package
java -jar target/frontend-app-1.0.0-RELEASE.jar --spring.profiles.active=dev
```
In development mode, the backend will run on port `8080`.

### Frontend
To launch the frontend you can run the application from the IDE **or** package it and run the built JAR (found in the target folder) with the `dev` profile.
Navigate to the `notes-frontend` folder and run the following commands:
```bash
mvn clean package
java -jar target/backend-app-1.0.0-RELEASE.jar --spring.profiles.active=dev
```

In development mode, the frontend will run on port `8081`.

## Building for Production
To create and launch the containerized production-ready app, navigate to the `production` folder and run the following command:
```bash
docker-compose up --build
```

Only the frontend will be exposed on port `80`. Navigate to `http://localhost:80` in your browser.
**Important:** There is no HTTPS for this project, ensure the connection is HTTP.
**Important:** Wait a while for services to fully start.

## Testing
The frontend app has unit tests using JUnit. 
To run the tests you can simply run the following command:
```bash
mvn test
```
These integration tests connect to the actual development backend and database.
**Important:** Make sure both the development backend service and the database are running before executing tests, or they will fail.
