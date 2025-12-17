package com.finance.personal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Personal Finance Manager API")
                        .version("1.0.0")
                        .description("Personal Finance Manager API featuring management of users, accounts and transactions")
                        .contact(new Contact()
                                .name("Bernardo Mecabo")
                                .url("https://github.com/bernardommecabo/")
                                .email("contato.bernardo.mecabo@gmail.com")));
    }
}
