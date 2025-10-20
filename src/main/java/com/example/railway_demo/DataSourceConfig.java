package com.example.railway_demo;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        // Read the DATABASE_URL environment variable provided by Railway
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        // Extract the username and password from the URI
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];

        // Construct the final JDBC URL from the parts of the URI
        // This transforms "postgres://" to "jdbc:postgresql://"
        String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        // Use DataSourceBuilder to create and configure the DataSource
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();
    }
}
