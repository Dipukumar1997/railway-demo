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
        // Read the database URL from the environment
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        // Initialize username and password as null
        String username = null;
        String password = null;

        // Check if user info is present in the URL
        String userInfo = dbUri.getUserInfo();
        if (userInfo != null) {
            // If it exists, split it into username and password
            String[] userParts = userInfo.split(":");
            username = userParts[0];
            password = userParts[1];
        }

        // Construct the JDBC URL
        String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        // Build the DataSource object
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
                .url(jdbcUrl);
        
        // Only set username and password if they were found in the URL
        if (username != null) {
            dataSourceBuilder.username(username);
        }
        if (password != null) {
            dataSourceBuilder.password(password);
        }

        return dataSourceBuilder.build();
    }
}
