Docker Commands

1. Pull the base image manually (to check availability)
   docker pull eclipse-temurin:23-jdk
2. Build the Docker Image
   docker build --no-cache -t spring-security-service .
3. Run the Container
   docker run -d -p 8080:8080 spring-security-service
4. Check logs (if needed)
   docker logs -f <container_id>