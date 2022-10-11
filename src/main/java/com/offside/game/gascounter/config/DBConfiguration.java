package com.offside.game.gascounter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DBConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource liquibaseDataSource() {
        return DataSourceBuilder.create().build();
    }

}
