## spring-boot-multidatabse

https://www.baeldung.com/spring-data-jpa-multiple-databases

1. create entities
    -- lets assume we want to connect to two dbs
    -- user & product
    -- anntotate with @entity
3. create jpa repositories
4. confiigure jpa for 2 classes user & product


### COnfigure JPA 

In each configuration class, we’ll need to define the following interfaces for User:

  * DataSource
  * EntityManagerFactory (userEntityManager)
  * TransactionManager (userTransactionManager)

#### code
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory", transactionManagerRef = "userTransactionManager", basePackages = "com.ahmed.repo.user")
public class UserDatasource {

	@Bean(name = "userDataSource")
	@ConfigurationProperties(prefix = "spring.user.datasource")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "userEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("userDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		return builder.dataSource(dataSource).properties(properties).packages("com.ahmed.model.user").persistenceUnit("User")
				.build();
	}

	@Primary
	@Bean(name = "userTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("userEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}

### properties 

Spring Boot can simplify the configuration above.

By default, Spring Boot will instantiate its default DataSource with the configuration properties prefixed by spring.datasource.*:

spring.datasource.jdbcUrl = [url]
spring.datasource.username = [username]
spring.datasource.password = [password]
Copy
We now want to keep using the same way to configure the second DataSource, but with a different property namespace:

spring.user.datasource.jdbc-url=jdbc:mysql://localhost:3306/dev
spring.user.datasource.username=root
spring.user.datasource.password=T@uq33r@007
spring.user.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.user.database-platform=org.hibernate.dialect.H2Dialect

spring.booking.datasource.jdbc-url=jdbc:mysql://localhost:3306/testing
spring.booking.datasource.username=root
spring.booking.datasource.password=T@uq33r@007
spring.booking.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.booking.database-platform=org.hibernate.dialect.H2Dialect
