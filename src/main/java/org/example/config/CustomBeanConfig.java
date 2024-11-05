package org.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfig {

    @Bean
    @ConditionalOnProperty(name = "app.feature.enabled",havingValue = "true")
    public String thisIsMyFirstConditionalBean() {
        return "This bean is available because app.feature.enabled is true!";
    }
}