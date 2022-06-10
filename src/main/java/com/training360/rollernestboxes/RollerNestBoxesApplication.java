package com.training360.rollernestboxes;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RollerNestBoxesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollerNestBoxesApplication.class, args);
    }

    @Bean
    public OpenAPI createOpenApiUI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roller nest boxes and nesting API")
                        .version("0.0.1-SNAPSHOT")
                        .description("Operations on nest boxes and nesting")
                        .contact(new Contact().name("Attila Ferenc")));
    }
}
