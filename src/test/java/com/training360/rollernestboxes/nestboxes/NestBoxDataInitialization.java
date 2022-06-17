package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.model.*;

import java.time.LocalDate;
import java.util.List;

public class NestBoxDataInitialization {

    public List<NestBox> createNestBoxes() {
        NestBox nestBox1547c = new NestBox("1547/c",
                new NestBoxPlacement(LocalDate.of(2022, 3, 10),
                        new NestBoxParameters(new Coordinates(758626,
                                260377),
                                "Quercus robur",
                                5.5,
                                Quarter.NORTH,
                                NestBoxType.OLD),
                        "John Doe"),
                Condition.GOOD,
                "Maybe a little bit close to the road 33");
        NestBox nestBox2256 = new NestBox("2256",
                new NestBoxPlacement(LocalDate.of(2022, 3, 10),
                        new NestBoxParameters(new Coordinates(757626,
                                261377),
                                "Robinia pseudoacacia",
                                7.5,
                                Quarter.SOUTHEAST,
                                NestBoxType.ROLLER_LIFE_PLUS_2017),
                        "John Doe"),
                Condition.MUST_BE_REPAIRED,
                "Difficult to climb");
        NestBox nestBox1742a = new NestBox("1742/A",
                new NestBoxPlacement(LocalDate.of(2017, 2, 14),
                        new NestBoxParameters(new Coordinates(754789,
                                259472),
                                "Robinia pseudoacacia",
                                4.5,
                                Quarter.SOUTH,
                                NestBoxType.OLD),
                        "John Doe"),
                Condition.MUST_BE_REPLACED,
                "");
        NestBox nestBox1115 = new NestBox("1115",
                new NestBoxPlacement(LocalDate.of(2014, 9, 25),
                        new NestBoxParameters(new Coordinates(761911,
                                257453),
                                "Fraxinus sp.",
                                6.8,
                                Quarter.WEST,
                                NestBoxType.OLD),
                        "John Doe"),
                new NestBoxExpiration(LocalDate.of(2019, 7, 14),
                        "The tree was cut down, the nest box dissapeared",
                        "John Doe"),
                Condition.EXPIRED,
                "");
        NestBox nestBox1214 = new NestBox("1214",
                new NestBoxPlacement(LocalDate.of(2015, 2, 14),
                        new NestBoxParameters(new Coordinates(768904,
                                264123),
                                "középfeszültségű oszlop",
                                4,
                                Quarter.SOUTHEAST,
                                NestBoxType.OLD),
                        "John Doe"),
                Condition.UNSUITABLE_FOR_ROLLER,
                "Missing roof, used regularly by kestrels");
        return List.of(nestBox1547c, nestBox2256, nestBox1742a, nestBox1115, nestBox1214);
    }
}
