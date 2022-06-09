package com.training360.rollernestboxes;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RollerNestboxesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollerNestboxesApplication.class, args);
    }

    @Bean
    public OpenAPI createOpenApiUI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roller nestboxes and nestings API")
                        .version("0.0.1")
                        .description("OPerations with nestboxes and nestings")
                        .contact(new Contact().name("Attila Ferenc")));
    }
}
