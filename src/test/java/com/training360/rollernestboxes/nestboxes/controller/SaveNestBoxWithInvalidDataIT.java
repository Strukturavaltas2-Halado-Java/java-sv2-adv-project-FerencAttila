package com.training360.rollernestboxes.nestboxes.controller;

import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesCommand;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPuttingCommand;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from nests", "delete from nest_boxes"})
class SaveNestBoxWithInvalidDataIT {

    @Autowired
    WebTestClient client;

    @Test
    void saveNestBoxWithTooLowXCoordinateTest() {
        NestBoxPuttingCommand nestBox = new NestBoxPuttingCommand("1BC",
                new CoordinatesCommand(426399, 260377),
                Quarter.N,
                5.5);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(nestBox)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("The minimum value of the x coordinate is 426400"));
    }

    @Test
    void saveNestBoxWithTooHighXCoordinateTest() {
        NestBoxPuttingCommand nestBox = new NestBoxPuttingCommand("1BC",
                new CoordinatesCommand(937501, 260377),
                Quarter.N,
                5.5);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(nestBox)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("The maximum value of the x coordinate is 937500"));
    }

    @Test
    void saveNestBoxWithTooLowYCoordinateTest() {
        NestBoxPuttingCommand nestBox = new NestBoxPuttingCommand("1BC",
                new CoordinatesCommand(426400, 42999),
                Quarter.N,
                5.5);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(nestBox)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("The minimum value of the y coordinate is 43000"));
    }

    @Test
    void saveNestBoxWithTooHighYCoordinateTest() {
        NestBoxPuttingCommand nestBox = new NestBoxPuttingCommand("1BC",
                new CoordinatesCommand(937500, 361401),
                Quarter.N,
                5.5);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(nestBox)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("The maximum value of the y coordinate is 361400"));
    }

    @Test
    void saveNestBoxTooLowTest() {
        NestBoxPuttingCommand nestBox = new NestBoxPuttingCommand("1BC",
                new CoordinatesCommand(937500, 361400),
                Quarter.N,
                0.9);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(nestBox)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("You cannot place a roller nest box lower then 1 meter!"));
    }

    @Test
    void saveNestBoxTooHighTest() {
        NestBoxPuttingCommand nestBox = new NestBoxPuttingCommand("1BC",
                new CoordinatesCommand(937500, 361400),
                Quarter.N,
                10.1);

        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(nestBox)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(problem -> assertThat(problem.toString())
                        .contains("You cannot place a roller nest box higher then 10 meters!"));
    }
}
