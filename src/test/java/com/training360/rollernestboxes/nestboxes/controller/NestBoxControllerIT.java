package com.training360.rollernestboxes.nestboxes.controller;

import com.training360.rollernestboxes.NestBoxDataInitialization;
import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesCommand;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPuttingCommand;
import com.training360.rollernestboxes.nestboxes.dtos.UpdateNestBoxCommand;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from nests", "delete from nest_boxes"})
class NestBoxControllerIT {

    @Autowired
    NestBoxRepository repository;

    @Autowired
    WebTestClient client;

    @BeforeEach
    void setUp() {
        repository.saveAll(new NestBoxDataInitialization().initTestDatabase());
    }

    @Test
    void findAllNestBoxesTest() {
        client.get()
                .uri("/api/nest-boxes/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NestBoxDto.class)
                .value(list -> assertThat(list)
                        .hasSize(5)
                        .extracting(NestBoxDto::getNestBoxNumber)
                        .containsExactlyInAnyOrder("1547/c", "2256", "1742/A", "1115", "1214"));
    }

    @Test
    void findNestBoxByNestBoxNumberTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/nest-boxes").queryParam("nest-box-number", "2256").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NestBoxDto.class)
                .value(NestBoxDto::getNestBoxNumber, equalTo("2256"));
    }

    @Test
    void findNestBoxByNestBoxNumberDoesNotExistsTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/nest-boxes").queryParam("nest-box-number", "2257").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(Problem::getTitle, equalTo("Nest box not found in the database"))
                .value(Problem::getDetail, equalTo("Nest box with nest box number 2257 does not exists!"));
    }

    @Test
    void saveNestBoxTest() {
        NestBoxPuttingCommand command = new NestBoxPuttingCommand(
                "2258",
                new CoordinatesCommand(758962, 356123),
                Quarter.SE,
                5.5);
        client.post()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NestBoxDto.class)
                .value(NestBoxDto::getNestBoxNumber, equalTo("2258"));
    }

    @Test
    void updateNestBoxParametersTest() {
        UpdateNestBoxCommand command = new UpdateNestBoxCommand(
                "2256",
                Quarter.NE,
                6.5);

        client.put()
                .uri("/api/nest-boxes")
                .bodyValue(command)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NestBoxDto.class)
                .value(NestBoxDto::getDirection, equalTo(Quarter.NE));
    }

    @Test
    void deleteNestBoxTest() {
        client.delete()
                .uri(uriBuilder -> uriBuilder.path("/api/nest-boxes").queryParam("nest-box-number", "1214").build())
                .exchange()
                .expectStatus().isNoContent();

        client.get()
                .uri("/api/nest-boxes/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NestBoxDto.class)
                .value(list -> assertThat(list)
                        .hasSize(4)
                        .extracting(NestBoxDto::getNestBoxNumber)
                        .containsExactlyInAnyOrder("1547/c", "2256", "1742/A", "1115"));
    }
}