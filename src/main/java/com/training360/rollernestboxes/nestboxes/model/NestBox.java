package com.training360.rollernestboxes.nestboxes.model;

import com.training360.rollernestboxes.nest.model.Nest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nest_boxes")
public class NestBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nest_box_id")
    private Long nestBoxId;

    @Column(name = "nest_box_number", nullable = false, unique = true)
    private String nestBoxNumber;

    @Embedded
    @AttributeOverride(name = "eovX", column = @Column(name = "eov_x", nullable = false))
    @AttributeOverride(name = "eovY", column = @Column(name = "eov_y", nullable = false))
    private Coordinates coordinates;

    @Enumerated(EnumType.STRING)
    private Quarter direction;

    @Column(precision = 1)
    private double height;

    @OneToMany(mappedBy = "nestBox", cascade = CascadeType.PERSIST)
    private Set<Nest> nests = new HashSet<>();

    public NestBox(String nestBoxNumber, Coordinates coordinates, Quarter direction, double height) {
        this.nestBoxNumber = nestBoxNumber;
        this.coordinates = coordinates;
        this.direction = direction;
        this.height = height;
    }

    public void addNest(Nest nest) {
        nests.add(nest);
    }
}
