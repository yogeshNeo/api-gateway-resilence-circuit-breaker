/*
package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

// @Configuration
// @EnableR2dbcRepositories(basePackages = "com.gradle.reactive.repository")
public class MySqlConfiguration extends AbstractR2dbcConfiguration {

    @Autowired
    private CustomR2bcProperties customR2bcProperties;

  //  @Bean("connectionFactory")
    public ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder().host(customR2bcProperties.getHost())
                .port(Integer.parseInt(customR2bcProperties.getPort()))
                .database(customR2bcProperties.getDatabase().get("app"))
                .username(customR2bcProperties.getUsername())
                .password(customR2bcProperties.getPassword()).build());
    }

   // @Bean
    ReactiveTransactionManager transactionManager() {
        ConnectionFactory connectionFactory = connectionFactory();
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer r2dbcScriptDatabaseInitializer() {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory());
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }
}
*/
