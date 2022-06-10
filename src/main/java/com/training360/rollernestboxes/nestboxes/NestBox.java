package com.training360.rollernestboxes.nestboxes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nest_boxes")
public class NestBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nest_box_id")
    private String nestBoxId;

    @Embedded
    @AttributeOverride(name = "dateOfPlacement", column = @Column(name = "placement_date"))
    @AttributeOverride(name = "nestBoxParameters.coordinates.eovX", column = @Column(name = "eov_x"))
    @AttributeOverride(name = "nestBoxParameters.coordinates.eovY", column = @Column(name = "eov_y"))
    @AttributeOverride(name = "nestBoxParameters.holder", column = @Column(name = "holder"))
    @AttributeOverride(name = "nestBoxParameters.height", column = @Column(name = "height"))
    @AttributeOverride(name = "nestBoxParameters.orientation", column = @Column(name = "orientation"))
    @AttributeOverride(name = "nestBoxParameters.nestBoxType", column = @Column(name = "nest_box_type"))
    @AttributeOverride(name = "reporterOfPlacement", column = @Column(name = "placement_reporter"))
    private NestBoxPlacement nestBoxPlacement;

    @Embedded
    @AttributeOverride(name = "dateOfExpiry", column = @Column(name = "expiry_date"))
    @AttributeOverride(name = "descriptionOfExpiry", column = @Column(name = "expiry_desc"))
    @AttributeOverride(name = "reporterOfExpiry", column = @Column(name = "expiry_reporter"))
    private NestBoxExpiration nestBoxExpiration;

    @Column(name = "nest_box_condition")
    @Enumerated(value = EnumType.STRING)
    private Condition condition;

    private String notes;

    //@OneToMany
    //private List<Nesting> nestings;
}
