# user-service
- spring-boot
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- spring-boot-starter-data-rest
- spring-boot-starter-validation
- spring-boot-starter-actuator

## spring-boot-starter
1. **@SpringBootApplication**
   Purpose: Marks the main class of a Spring Boot application. It's a combination of:
   + __@Configuration:__  Marks this class as a configuration class.
   + __@EnableAutoConfiguration:__ Enables Spring Boot's auto-configuration.
   + __@ComponentScan:__ Scans components, configurations, and services in the package and sub-packages.
2. **Dependency Injection Annotations**
   Marks a class as a Spring-managed bean.
   * __@Component__
   * __@Service__
   * __@Repository__

## spring-boot-starter-web

## spring-boot-actuator

## Docker Commands
docker build -t user-service .
docker run -p 8080:8080 user-service

## Docker compose commands

# Build and Run
docker-compose up --build

# Run & Build in detached mode
docker-compose up --build -d

# Start in detached mode
docker-compose up --build -d

# Check container logs
docker logs -f user-service
docker-compose logs -f

# Verify MySql
docker exec -it mysql mysql -uroot -proot
SHOW DATABASES;

# View running containers
docker ps

# Stop all containers
docker-compose down
docker-compose down -v