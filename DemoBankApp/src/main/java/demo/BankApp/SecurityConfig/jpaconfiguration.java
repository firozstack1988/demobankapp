package com.firoztechi.payrollapp.configdb;

import javax.activation.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class jpaconfiguration {

	@Bean
    @ConfigurationProperties("spring.datasource.dbName")
    public DataSourceProperties syncDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource syncDataSource() {
        return (DataSource) syncDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
}
