package com.training360.rollernestboxes;

import com.training360.rollernestboxes.nest.dtos.SurveyCommand;
import com.training360.rollernestboxes.nest.exceptions.SurveyIsNotUniqueException;
import com.training360.rollernestboxes.nest.model.Nest;
import com.training360.rollernestboxes.nest.repository.NestRepository;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.exceptions.CannotDeleteNestBoxException;
import com.training360.rollernestboxes.nestboxes.exceptions.NestBoxNotFoundException;
import com.training360.rollernestboxes.nestboxes.mapper.NestBoxMapperImpl;
import com.training360.rollernestboxes.nestboxes.model.Coordinates;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zalando.problem.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NestBoxNestServiceTest {

    @Mock
    NestBoxRepository nestBoxRepository;

    @Mock
    NestRepository nestRepository;

    @Mock
    NestBoxMapperImpl nestBoxMapper;

    @InjectMocks
    NestBoxNestService service;

    NestBox nestBox;

    Nest nest;

    @BeforeEach
    void setUp() {
        nestBox = new NestBox(1L,
                "2560/a",
                new Coordinates(758412, 256879),
                Quarter.N,
                5.5);

        nest = new Nest(nestBox,
                LocalDate.of(2021, 6, 14),
                "Passer montanus",
                0,
                "John Doe");
    }

    @Test
    void findAllNestBoxesTest() {
        when(nestBoxRepository.findAll())
                .thenReturn(List.of(
                        nestBox,
                        new NestBox(2L,
                                "1658",
                                new Coordinates(745891, 255124),
                                Quarter.NE,
                                6.0)));
        when(nestBoxMapper.toNestBoxDto(any())).thenCallRealMethod();
        when(nestBoxMapper.toCoordinatesDto(any())).thenCallRealMethod();

        assertThat(service.findAllNestBoxes())
                .hasSize(2)
                .extracting(NestBoxDto::getNestBoxNumber)
                .containsExactlyInAnyOrder("2560/a", "1658");
    }

    @Test
    void findNestsInNestBoxDoesNotExistsTest() {
        assertThatExceptionOfType(NestBoxNotFoundException.class)
                .isThrownBy(() -> service.findNests(Optional.of("1245"), Optional.empty()))
                .hasFieldOrPropertyWithValue("status", Status.BAD_REQUEST)
                .withMessage("Nest box not found in the database: Nest box with nest box number 1245 does not exists!");
    }

    @Test
    void saveNestIsNotUniqueTest() {
        SurveyCommand command = new SurveyCommand(
                "2560/a",
                LocalDate.of(2021, 6, 14),
                "Passer montanus",
                0,
                "John Doe");

        when(nestRepository.findNestByNestBox_NestBoxNumber("2560/a"))
                .thenReturn(List.of(nest));

        assertThatExceptionOfType(SurveyIsNotUniqueException.class)
                .isThrownBy(() -> service.saveNest(command))
                .withMessage("Survey is not unique: There is another observation in the database for nest box number 2560/a on 2021-06-14 by John Doe");
    }

    @Test
    void cannotDeleteNestBoxTest() {
        nestBox.addNest(nest);

        when(nestBoxRepository.findNestBoxByNestBoxNumber("2560/a"))
                .thenReturn(Optional.of(nestBox));

        assertThatExceptionOfType(CannotDeleteNestBoxException.class)
                .isThrownBy(() -> service.deleteNestBox("2560/a"))
                .withMessage("Can not delete nest box: Nest box with number 2560/a has nests, thus it can not be deleted");
    }
}