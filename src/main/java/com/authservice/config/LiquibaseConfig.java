package com.authservice.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/master.xml"); // Путь к главному changelog-файлу
        liquibase.setContexts("dev,prod"); // Профили, для которых применяются миграции
        liquibase.setShouldRun(true); // Включить Liquibase
        return liquibase;
    }
}