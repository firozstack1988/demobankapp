package com.firoztechi.payrollapp.configdb;


import java.util.Objects;

import javax.activation.DataSource;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"com.firoztechi.payrollapp.scheduler.component"},
		entityManagerFactoryRef = "dbNameEntityManagerFactory",
		transactionManagerRef = "dbNameTransactionManager"
)
public class jpadatasourceconfig {
	    @Bean
	    @Primary
	    public LocalContainerEntityManagerFactoryBean dbNameEntityManagerFactory(
	      @Qualifier("dbNameDataSource") DataSource dataSource,
	      EntityManagerFactoryBuilder builder
	    ) {
	        return (builder
	          .dataSource()
	          .packages("com.firoztechi.payrollapp.scheduler.component")
	          .build());
	    }

	    @Bean
	    public PlatformTransactionManager syncTransactionManager(
	      @Qualifier("dbNameEntityManagerFactory") LocalContainerEntityManagerFactoryBean dbNameEntityManagerFactory) {
	        return new JpaTransactionManager(Objects.requireNonNull(dbNameEntityManagerFactory.getObject()));
	    }
}
