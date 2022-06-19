package com.training360.rollernestboxes.nest.repository;

import com.training360.rollernestboxes.NestBoxDataInitialization;
import com.training360.rollernestboxes.nest.model.Nest;
import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(statements = {"delete from nests", "delete from nest_boxes"})
class NestRepositoryIT {

    @Autowired
    NestRepository nestRepository;

    @Autowired
    NestBoxRepository nestBoxRepository;

    @BeforeEach
    void setUp() {
        nestBoxRepository.saveAll(new NestBoxDataInitialization().initTestDatabase());
    }

    @Test
    void findAllNestsTest() {
        assertThat(nestRepository.findAllNestsByNestBoxNumberAndSpecies(Optional.empty(), Optional.empty()))
                .hasSize(6)
                .extracting(Nest::getNumberOfNestlings)
                .containsExactlyInAnyOrder(4, 4, 0, 5, 5, 0);
    }

    @Test
    void findAllByNestBoxNumber() {
        assertThat(nestRepository.findAllNestsByNestBoxNumberAndSpecies(Optional.of("1742/A"), Optional.empty()))
                .hasSize(3)
                .extracting(Nest::getDateOfSurvey)
                .containsExactlyInAnyOrder(LocalDate.of(2017, 6, 30),
                        LocalDate.of(2018, 7, 1),
                        LocalDate.of(2019, 6, 17));
    }

    @Test
    void findAllBySpecies() {
        assertThat(nestRepository.findAllNestsByNestBoxNumberAndSpecies(Optional.empty(), Optional.of("Passer montanus")))
                .hasSize(1)
                .extracting(Nest::getNumberOfNestlings)
                .containsOnly(0);
    }

    @Test
    void findAllNestsByNestBoxNumberAndSpeciesTest() {
        assertThat(nestRepository.findAllNestsByNestBoxNumberAndSpecies(Optional.of("1742/A"), Optional.of("Coracias garrulus")))
                .hasSize(2)
                .extracting(Nest::getDateOfSurvey)
                .containsExactlyInAnyOrder(LocalDate.of(2018, 7, 1),
                        LocalDate.of(2019, 6, 17));
    }

    @Test
    void findNestByNestBox_NestBoxNumberTest() {
        assertThat(nestRepository.findNestByNestBox_NestBoxNumber("1742/A"))
                .hasSize(3)
                .extracting(Nest::getDateOfSurvey)
                .containsExactlyInAnyOrder(LocalDate.of(2017, 6, 30),
                        LocalDate.of(2018, 7, 1),
                        LocalDate.of(2019, 6, 17));
    }
}