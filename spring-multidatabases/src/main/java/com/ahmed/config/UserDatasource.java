package com.ahmed.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

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
//		properties.put("hibernate.hbm2ddl.auto", "update");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
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
