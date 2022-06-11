package com.training360.rollernestboxes.nesting.model;

import com.training360.rollernestboxes.nestboxes.model.NestBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nesting")
public class Nesting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private NestBox nestBox;

    @Embedded
    @AttributeOverride(name = "dateOfSurvey", column = @Column(name = "survey_date", nullable = false))
    @AttributeOverride(name = "nestingSpecies", column = @Column(name = "species"))
    @AttributeOverride(name = "numberOfEggs", column = @Column(name = "eggs_number"))
    @AttributeOverride(name = "numberOfNestlings", column = @Column(name = "nestlings_number"))
    @AttributeOverride(name = "ageOfNestlings", column = @Column(name = "nestlings_age"))
    @AttributeOverride(name = "mortality.numberOfScrambledEggs", column = @Column(name = "scrambled_eggs_number"))
    @AttributeOverride(name = "mortality.numberOfDeadNestlings", column = @Column(name = "dead_nestlings_number"))
    @AttributeOverride(name = "mortality.numberOfDeadAdults", column = @Column(name = "dead_adults_number"))
    @AttributeOverride(name = "mortality.description", column = @Column(name = "mortality_desc"))
    private NestingParameters nestingParameters;

    @Column(name = "notes")
    private String notesOnNesting;

    private String observer;

    public Nesting(NestBox nestBox, NestingParameters nestingParameters, String notesOnNesting, String observer) {
        this.nestBox = nestBox;
        this.nestingParameters = nestingParameters;
        this.notesOnNesting = notesOnNesting;
        this.observer = observer;
    }
}
