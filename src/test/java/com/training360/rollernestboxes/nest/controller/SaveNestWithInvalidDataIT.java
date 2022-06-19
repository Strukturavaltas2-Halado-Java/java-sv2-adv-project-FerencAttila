package com.training360.rollernestboxes.nest.controller;

import com.training360.rollernestboxes.nest.dtos.SurveyCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from nests", "delete from nest_boxes"})
class SaveNestWithInvalidDataIT {

    @Autowired
    WebTestClient client;

    @Test
    void saveNestWithShortScientificName() {
        SurveyCommand command = new SurveyCommand(
                "1547/c",
                LocalDate.of(2021, 6, 14),
                "Pass",
                0,
                "John Doe");

        client.post()
                .uri("/api/nests")
                .bodyValue(command)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("Scientific name of nestig species must be consist of minimum 5 and maximum of 45 characters!"));
    }

    @Test
    void saveNestWithNegativeNumberOfNestlingsTest() {
        SurveyCommand command = new SurveyCommand(
                "1547/c",
                LocalDate.of(2021, 6, 14),
                "Passer montanus",
                -1,
                "John Doe");

        client.post()
                .uri("/api/nests")
                .bodyValue(command)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("Number of nestlings must be positive integer or zero!"));
    }

    @Test
    void saveNestWithTooMuchNestlingsTest() {
        SurveyCommand command = new SurveyCommand(
                "1547/c",
                LocalDate.of(2021, 6, 14),
                "Passer montanus",
                21,
                "John Doe");

        client.post()
                .uri("/api/nests")
                .bodyValue(command)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("Number of nestlings must be less or equal to 20!"));
    }

    @Test
    void saveNestWithShortObserverNameTest() {
        SurveyCommand command = new SurveyCommand(
                "1547/c",
                LocalDate.of(2021, 6, 14),
                "Passer montanus",
                -1,
                "John");

        client.post()
                .uri("/api/nests")
                .bodyValue(command)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("Person name must be consist of minimum 5 and maximum of 100 characters!"));
    }
}
