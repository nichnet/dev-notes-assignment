# Spring Boot Assignment

![image]()


## Prerequisites
Before attempting to run the applications, ensure the following are installed on your local machine:

- [OpenJDK 17](https://openjdk.org/projects/jdk/17/) (for both the backend/frontend apps)
- Maven (for building)
- Docker (for the dev database container and production setup, optionally install [Docker Desktop](https://www.docker.com/products/docker-desktop/))

## Running in Development Mode

### Database
To launch the containerized database, navigate to the `database` folder and run the following command:
```bash
docker-compose up --build
```
In development mode, the datbase container will expose the mysql instance on port `3036`.

### Backend
```bash
mvn clean install
mvn spring-boot:run
```

In development mode, the backend will run on port `8080`.

### Frontend

```bash
mvn clean install
mvn spring-boot:run
```

In development mode, the frontend will run on port `8081`.


## Building for Production
To create and launch the containerized production-ready app, navigate to the `production` folder and run the following command:
```bash
docker-compose up --build
```

Only the frontend will be exposed on port `80`.

## Testing
