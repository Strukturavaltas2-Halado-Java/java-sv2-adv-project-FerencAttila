package com.training360.rollernestboxes.nest.controller;

import com.training360.rollernestboxes.NestBoxDataInitialization;
import com.training360.rollernestboxes.nest.dtos.NestDto;
import com.training360.rollernestboxes.nest.dtos.SurveyCommand;
import com.training360.rollernestboxes.nest.dtos.ZoologyDataDto;
import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from nests", "delete from nest_boxes"})
class NestControllerIT {

    @Autowired
    NestBoxRepository repository;

    @Autowired
    WebTestClient client;

    @BeforeEach
    void setUp() {
        repository.saveAll(new NestBoxDataInitialization().initTestDatabase());
    }


    @Test
    void findAllNestsTest() {
        client.get()
                .uri("/api/nests")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NestDto.class)
                .value(list -> assertThat(list).hasSize(6)
                        .extracting(NestDto::getNestBoxNumber)
                        .containsExactlyInAnyOrder("1547/c", "2256", "1742/A", "1742/A", "1742/A", "1115"));
    }

    @Test
    void findAllNestsInGivenNestBoxTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/nests")
                        .queryParam("nest-box-number", "1742/A")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NestDto.class)
                .value(list -> assertThat(list)
                        .hasSize(3)
                        .extracting(NestDto::getNestBoxNumber)
                        .containsOnly("1742/A"));
    }

    @Test
    void findAllNestsByGivenSpeciesTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/nests")
                        .queryParam("species", "Coracias garrulus")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NestDto.class)
                .value(list -> assertThat(list)
                        .hasSize(3)
                        .extracting(NestDto::getSpecies)
                        .containsOnly("Coracias garrulus"));
    }

    @Test
    void findAllNestsInGivenNestBoxByGivenSpeciesTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/nests")
                        .queryParam("nest-box-number", "1742/A")
                        .queryParam("species", "Coracias garrulus")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NestDto.class)
                .value(list -> assertThat(list)
                        .hasSize(2)
                        .extracting(NestDto::getSpecies)
                        .containsOnly("Coracias garrulus"))
                .value(list -> assertThat(list)
                        .hasSize(2)
                        .extracting(NestDto::getNestBoxNumber)
                        .containsOnly("1742/A"));
    }

    @Test
    void getAllNestsAsZoologyDataTest() {
        client.get()
                .uri("/api/nests/zoology-data")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ZoologyDataDto.class)
                .value(list -> assertThat(list)
                        .hasSize(6)
                        .extracting(ZoologyDataDto::getQuantity)
                        .containsExactlyInAnyOrder(4,4,0,5,5,0))
                .value(list -> assertThat(list)
                        .extracting(ZoologyDataDto::getActivity)
                        .containsOnly("Nestling in nest"));
    }

    @Test
    void saveNestTest() {
        SurveyCommand command = new SurveyCommand(
                "1547/c",
                LocalDate.of(2021,6,14),
                "Passer montanus",
                0,
                "John Doe");

        client.post()
                .uri("/api/nests")
                .bodyValue(command)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NestDto.class)
                .value(NestDto::getNestBoxNumber, equalTo("1547/c"));
    }
}