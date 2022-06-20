package com.training360.rollernestboxes.nestboxes.controller;

import com.training360.rollernestboxes.nestboxes.dtos.UpdateNestBoxCommand;
import com.training360.rollernestboxes.nestboxes.model.Coordinates;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import org.junit.jupiter.api.BeforeEach;
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
class UpdateNestBoxWithInvalidDataIT {

    @Autowired
    WebTestClient client;

    @BeforeEach
    void setUp() {
        NestBox nestBox = new NestBox("1547/c",
                new Coordinates(758626, 260377),
                Quarter.N,
                5.5);
        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(nestBox)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    @Test
    void updateNestBoxTooLowTest() {
        UpdateNestBoxCommand nestBox = new UpdateNestBoxCommand("1547/c", Quarter.SW, 0.9);

        client.put()
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
    void updateNestBoxTooHighTest() {
        UpdateNestBoxCommand nestBox = new UpdateNestBoxCommand("1547/c", Quarter.SW, 10.1);

        client.put()
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
