# Notes-Backend Dockerfile
FROM openjdk:17.0.2-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app/backend

COPY pom.xml /app/backend/pom.xml

COPY src /app/backend/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app/backend

COPY --from=build app/backend/target/notes-backend-1.0.0-RELEASE.jar /app/backend.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]
