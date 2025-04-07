# Notes-Backend Dockerfile
FROM openjdk:17.0.2-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app/frontend

COPY pom.xml /app/frontend/pom.xml

COPY src /app/frontend/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app/frontend

COPY --from=build app/frontend/target/notes-frontend-1.0.0-RELEASE.jar /app/frontend.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/frontend.jar"]
