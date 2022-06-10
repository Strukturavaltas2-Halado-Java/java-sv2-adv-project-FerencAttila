package com.training360.rollernestboxes.nestboxes.model;

import com.training360.rollernestboxes.nestboxes.Condition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private String notes;

    public NestBox(String nestBoxId, NestBoxPlacement nestBoxPlacement, Condition condition, String notes) {
        this.nestBoxId = nestBoxId;
        this.nestBoxPlacement = nestBoxPlacement;
        this.condition = condition;
        this.notes = notes;
    }

//@OneToMany
    //private List<Nesting> nesting = new ArrayList<>();
}
