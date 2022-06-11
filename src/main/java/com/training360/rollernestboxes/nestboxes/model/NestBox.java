package com.training360.rollernestboxes.nestboxes.model;

import com.training360.rollernestboxes.nesting.exceptions.NestingNotFoundException;
import com.training360.rollernestboxes.nesting.model.Nesting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nest_boxes")
public class NestBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nest_box_id", unique = true, nullable = false)
    private String nestBoxId;

    @Embedded
    @AttributeOverride(name = "dateOfPlacement", column = @Column(name = "placement_date", nullable = false))
    @AttributeOverride(name = "nestBoxParameters.coordinates.eovX", column = @Column(name = "eov_x", nullable = false))
    @AttributeOverride(name = "nestBoxParameters.coordinates.eovY", column = @Column(name = "eov_y", nullable = false))
    @AttributeOverride(name = "nestBoxParameters.holder", column = @Column(name = "holder"))
    @AttributeOverride(name = "nestBoxParameters.height", column = @Column(name = "height"))
    @AttributeOverride(name = "nestBoxParameters.orientation", column = @Column(name = "orientation"))
    @AttributeOverride(name = "nestBoxParameters.nestBoxType", column = @Column(name = "nest_box_type"))
    @AttributeOverride(name = "reporterOfPlacement", column = @Column(name = "placement_reporter", nullable = false))
    private NestBoxPlacement nestBoxPlacement;

    @Embedded
    @AttributeOverride(name = "dateOfExpiry", column = @Column(name = "expiry_date"))
    @AttributeOverride(name = "descriptionOfExpiry", column = @Column(name = "expiry_desc"))
    @AttributeOverride(name = "reporterOfExpiry", column = @Column(name = "expiry_reporter"))
    private NestBoxExpiration nestBoxExpiration;

    @Column(name = "nest_box_condition", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Condition condition;

    @Column(name = "notes")
    private String notesOnNestBox;

    @OneToMany(mappedBy = "nestBox", orphanRemoval = true)
    private List<Nesting> nesting = new ArrayList<>();

    public NestBox(String nestBoxId, NestBoxPlacement nestBoxPlacement, Condition condition, String notesOnNestBox) {
        this.nestBoxId = nestBoxId;
        this.nestBoxPlacement = nestBoxPlacement;
        this.condition = condition;
        this.notesOnNestBox = notesOnNestBox;
    }

    public void addNesting(Nesting nesting) {
        this.nesting.add(nesting);
    }

    public void removeNesting(long nestingId) {
        Nesting actual = this.nesting.stream()
                .filter(n -> n.getId() == nestingId)
                .findFirst()
                .orElseThrow(() -> new NestingNotFoundException(nestingId));
        this.nesting.remove(actual);
    }
}
