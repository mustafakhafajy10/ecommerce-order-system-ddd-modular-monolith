package com.champsoft.vrms2432352.shared.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGroupsConfig {
    @Bean
    GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder()
            .group("customers")
            .pathsToMatch("/api/customers/**")
            .build();
    }

    @Bean
    GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
            .group("products")
            .pathsToMatch("/api/products/**")
            .build();
    }

    @Bean
    GroupedOpenApi inventoryApi() {
        return GroupedOpenApi.builder()
            .group("inventory")
            .pathsToMatch("/api/inventory-items/**")
            .build();
    }

    @Bean
    GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder()
            .group("orders")
            .pathsToMatch("/api/orders/**")
            .build();
    }
}
