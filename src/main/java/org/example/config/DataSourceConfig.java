package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public ConnectionConfig connectionConfig() {
        return new ConnectionConfig();
    }
}