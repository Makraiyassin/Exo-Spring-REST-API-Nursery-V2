
# syntax=docker/dockerfile:1

FROM openjdk:18-jdk-alpine3.14
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
EXPOSE 8081
## docker build -t spring-app-docker .