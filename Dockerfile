#FROM openjdk:18-jdk-alpine3.14
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY src ./src
#CMD ["./mvnw", "spring-boot:run"]
#EXPOSE 8081
### docker build -t spring-app-docker .

FROM  openjdk:18-jdk-alpine3.14
ARG JAVA_JAR=target/*.jar
ADD ${JAVA_JAR} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
