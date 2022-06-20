package com.training360.rollernestboxes.nestboxes.validations.beanvalidations;

import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPuttingCommand;
import com.training360.rollernestboxes.nestboxes.dtos.UpdateNestBoxCommand;
import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomNestBoxBeanValidationIT {

    @MockBean
    NestBoxRepository repository;

    @Autowired
    WebTestClient client;

    @Test
    void NestBoxIdDoesNotExistsValidatorTest() {
        NestBoxPuttingCommand command = new NestBoxPuttingCommand();
        command.setNestBoxNumber("   2560/a   ");

        when(repository.existsNestBoxByNestBoxNumber("2560/a"))
                .thenReturn(true);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(Problem::getTitle, equalTo("Constraint Violation"))
                .value(problem -> assertThat(problem.toString())
                        .contains("Nest box id must be unique, cannot be null or empty string and consists maximum of 10 characters!"));
    }

    @Test
    void NestBoxIdExistsValidatorTest() {
        UpdateNestBoxCommand command = new UpdateNestBoxCommand();
        command.setNestBoxNumber("   2560/a   ");

        when(repository.existsNestBoxByNestBoxNumber("2560/a"))
                .thenReturn(false);

        client.put()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(Problem::getTitle, equalTo("Constraint Violation"))
                .value(problem -> assertThat(problem.toString())
                        .contains("Nest box id must be in the database, cannot be null or empty string and consists maximum of 10 characters!"));
    }

    @Test
    void NestBoxIdDoesNotExistsValidatorWithBlankNestBoxNumberTest() {
        NestBoxPuttingCommand command = new NestBoxPuttingCommand();
        command.setNestBoxNumber("      ");

        when(repository.existsNestBoxByNestBoxNumber(""))
                .thenReturn(true);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(Problem::getTitle, equalTo("Constraint Violation"))
                .value(problem -> assertThat(problem.toString())
                        .contains("Nest box id must be unique, cannot be null or empty string and consists maximum of 10 characters!"));
    }

    @Test
    void NestBoxIdExistsValidatorWithBlankNestBoxNumberTest() {
        UpdateNestBoxCommand command = new UpdateNestBoxCommand();
        command.setNestBoxNumber("      ");

        when(repository.existsNestBoxByNestBoxNumber(""))
                .thenReturn(false);

        client.put()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(Problem::getTitle, equalTo("Constraint Violation"))
                .value(problem -> assertThat(problem.toString())
                        .contains("Nest box id must be in the database, cannot be null or empty string and consists maximum of 10 characters!"));
    }

    @Test
    void NestBoxIdDoesNotExistsValidatorWithLongNestBoxNumberTest() {
        NestBoxPuttingCommand command = new NestBoxPuttingCommand();
        command.setNestBoxNumber("   123456789012   ");

        when(repository.existsNestBoxByNestBoxNumber("123456789012"))
                .thenReturn(true);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(Problem::getTitle, equalTo("Constraint Violation"))
                .value(problem -> assertThat(problem.toString())
                        .contains("Nest box id must be unique, cannot be null or empty string and consists maximum of 10 characters!"));
    }

    @Test
    void NestBoxIdExistsValidatorWithLongNestBoxNumberTest() {
        UpdateNestBoxCommand command = new UpdateNestBoxCommand();
        command.setNestBoxNumber("   123456789012   ");

        when(repository.existsNestBoxByNestBoxNumber("123456789012"))
                .thenReturn(false);

        client.put()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(Problem::getTitle, equalTo("Constraint Violation"))
                .value(problem -> assertThat(problem.toString())
                        .contains("Nest box id must be in the database, cannot be null or empty string and consists maximum of 10 characters!"));
    }
}
