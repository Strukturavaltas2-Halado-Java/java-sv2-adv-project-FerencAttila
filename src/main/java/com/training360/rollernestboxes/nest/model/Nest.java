package com.training360.rollernestboxes.nest.model;

import com.training360.rollernestboxes.nestboxes.model.NestBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nests")
public class Nest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nest_id")
    private Long nestId;

    @ManyToOne
    @JoinColumn(name = "nest_box_id")
    private NestBox nestBox;

    @Column(name = "date_of_survey", nullable = false)
    private LocalDate dateOfSurvey;

    private String species;

    @Column(name = "nestling_number")
    private int numberOfNestlings;

    @Column(nullable = false)
    private String observer;

    public Nest(NestBox nestBox, LocalDate dateOfSurvey, String species, int numberOfNestlings, String observer) {
        this.nestBox = nestBox;
        this.dateOfSurvey = dateOfSurvey;
        this.species = species;
        this.numberOfNestlings = numberOfNestlings;
        this.observer = observer;
    }
}
