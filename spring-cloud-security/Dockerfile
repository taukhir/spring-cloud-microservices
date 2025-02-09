# 1. Use an official Java runtime as base image
FROM eclipse-temurin:23-jdk

# 2. Set working directory
WORKDIR /app

# 3. Copy JAR file to the container
COPY build/libs/spring-cloud-security-0.0.1-SNAPSHOT.jar security-service.jar

# 4. Expose the application port
EXPOSE 8080

# 5. Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "security-service.jar"]