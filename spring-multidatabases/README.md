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
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
    basePackages = "com.baeldung.multipledb.dao.user", 
    entityManagerFactoryRef = "userEntityManager", 
    transactionManagerRef = "userTransactionManager"
)
public class PersistenceUserConfiguration {
    @Autowired
    private Environment env;
    
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(
          new String[] { "com.baeldung.multipledb.model.user" });

        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
          env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource userDataSource() {
 
        DriverManagerDataSource dataSource
          = new DriverManagerDataSource();
        dataSource.setDriverClassName(
          env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("user.jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          userEntityManager().getObject());
        return transactionManager;
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

spring.second-datasource.jdbcUrl = [url]
spring.second-datasource.username = [username]
spring.second-datasource.password = [password]
