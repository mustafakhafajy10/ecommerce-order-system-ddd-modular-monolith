package com.champsoft.vrms2432352.shared.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI ecommerceOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("E-Commerce Order System API")
                .description("DDD modular monolith with Order, Customer, Product, and Inventory bounded contexts.")
                .version("1.0.0")
                .contact(new Contact().name("Mustafa Amin")));
    }
}
