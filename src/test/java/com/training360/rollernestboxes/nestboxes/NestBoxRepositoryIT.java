package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(statements = {"delete from nest_boxes"})
class NestBoxRepositoryIT {

    @Autowired
    NestBoxRepository repository;

    @BeforeEach
    void init() {
        repository.saveAll(new NestBoxDataInitialization().createNestBoxes());
    }

    @Test
    void findByNestBoxIdIgnoreCaseTest() {
        assertEquals("1547/c", repository.findByNestBoxIdIgnoreCase("1547/C").get().getNestBoxId());
    }

    @Test
    void findAllByConditionIsNotTest() {
        assertThat(repository.findAllByConditionIsNot(Condition.GOOD))
                .hasSize(4)
                .extracting(NestBox::getCondition)
                .containsExactlyInAnyOrder(Condition.EXPIRED,
                        Condition.MUST_BE_REPAIRED,
                        Condition.MUST_BE_REPLACED,
                        Condition.UNSUITABLE_FOR_ROLLER);
    }

    @Test
    void findAllByConditionIsInTest() {
        assertThat(repository.findAllByConditionIsIn(Set.of(Condition.EXPIRED,
                Condition.GOOD,
                Condition.UNSUITABLE_FOR_ROLLER)))
                .hasSize(3)
                .extracting(NestBox::getNestBoxId)
                .containsExactlyInAnyOrder("1547/c", "1115", "1214");
    }

    @Test
    void existsNestBoxByNestBoxIdTest() {
        assertTrue(repository.existsNestBoxByNestBoxId("1115"));
        assertFalse(repository.existsNestBoxByNestBoxId("1116"));
    }

    @Test
    void existsNestBoxByNestBoxIdAndIsLivingTest() {
        assertTrue(repository.existsNestBoxByNestBoxIdAndIsLiving("1547/c"));
        assertFalse(repository.existsNestBoxByNestBoxIdAndIsLiving("1115"));
    }
}