package com.training360.rollernestboxes.nestboxes.repository;

import com.training360.rollernestboxes.NestBoxDataInitialization;
import com.training360.rollernestboxes.nestboxes.model.Coordinates;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NestBoxRepositoryIT {

    @Autowired
    NestBoxRepository repository;

    @BeforeEach
    void setUp() {
        repository.saveAll(new NestBoxDataInitialization().initTestDatabase());
    }

    @Test
    void findAllTest() {
        assertThat(repository.findAll())
                .hasSize(5)
                .extracting(NestBox::getNestBoxNumber)
                .containsExactlyInAnyOrder("1547/c", "2256", "1742/A", "1115", "1214");
    }

    @Test
    void findNestBoxByNestBoxNumberTest() {
        assertThat(repository.findNestBoxByNestBoxNumber("1742/A")).get()
                .extracting(NestBox::getDirection).isEqualTo(Quarter.S);
    }

    @Test
    void existsNestBoxByNestBoxNumberTest() {
        assertTrue(repository.existsNestBoxByNestBoxNumber("1547/c"));
        assertFalse(repository.existsNestBoxByNestBoxNumber("1"));
    }

    @Test
    void saveNestBox() {
        NestBox nestBox = new NestBox("1600",
                new Coordinates(748514,255689),
                Quarter.NW,
                1.5);

        assertThat(repository.save(nestBox))
                .extracting(NestBox::getNestBoxNumber)
                .isEqualTo("1600");
    }

    @Test
    void deleteNestBoxTest() {
        NestBox nestBox = new NestBox("1600",
                new Coordinates(748514,255689),
                Quarter.NW,
                1.5);
        repository.save(nestBox);
        repository.delete(nestBox);
        assertThat(repository.findAll())
                .hasSize(5)
                .extracting(NestBox::getNestBoxNumber)
                .containsExactlyInAnyOrder("1547/c", "2256", "1742/A", "1115", "1214");
    }
}