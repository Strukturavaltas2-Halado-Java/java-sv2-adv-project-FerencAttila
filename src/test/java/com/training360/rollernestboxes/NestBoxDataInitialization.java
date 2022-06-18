package com.training360.rollernestboxes;

import com.training360.rollernestboxes.nest.model.Nest;
import com.training360.rollernestboxes.nestboxes.model.Coordinates;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import com.training360.rollernestboxes.nestboxes.model.Quarter;

import java.time.LocalDate;
import java.util.List;

public class NestBoxDataInitialization {

    private NestBox nestBox1547c;

    private NestBox nestBox2256;

    private NestBox nestBox1742a;

    private NestBox nestBox1115;

    private NestBox nestBox1214;

    public List<NestBox> initTestDatabase() {
        createNestBoxes();
        return List.of(nestBox1547c, nestBox2256, nestBox1742a, nestBox1115, nestBox1214);
    }

    private void createNestBoxes() {
        nestBox1547c = new NestBox("1547/c",
                new Coordinates(758626, 260377),
                Quarter.N,
                5.5);
        nestBox2256 = new NestBox("2256",
                new Coordinates(757626, 261377),
                Quarter.SE,
                7.5);
        nestBox1742a = new NestBox("1742/A",
                new Coordinates(754789, 259472),
                Quarter.S,
                4.5);
        nestBox1115 = new NestBox("1115",
                new Coordinates(761911, 257453),
                Quarter.W,
                6.8);
        nestBox1214 = new NestBox("1214",
                new Coordinates(768904, 264123),
                Quarter.SE,
                4);
        createNests();
    }

    private void createNests() {
        Nest rollerNest = new Nest(nestBox1547c,
                LocalDate.of(2022, 6, 16),
                "Coracias garrulus",
                4,
                "John Doe");
        nestBox1547c.addNest(rollerNest);
        Nest jackdawNest = new Nest(nestBox2256,
                LocalDate.of(2022, 6, 16),
                "Corvus monedula",
                4,
                "John Doe");
        nestBox2256.addNest(jackdawNest);
        Nest sparrowNest = new Nest(nestBox1742a,
                LocalDate.of(2017, 6, 30),
                "Passer montanus",
                0,
                "John Doe");
        nestBox1742a.addNest(sparrowNest);
        Nest anotherRollerNest = new Nest(nestBox1742a,
                LocalDate.of(2018, 7, 1),
                "Coracias garrulus",
                5,
                "John Doe");
        nestBox1742a.addNest(anotherRollerNest);
        Nest nextRollerNest = new Nest(nestBox1742a,
                LocalDate.of(2019, 6, 17),
                "Coracias garrulus",
                5,
                "John Doe");
        nestBox1742a.addNest(nextRollerNest);
        Nest emptyNestBox = new Nest(nestBox1115,
                LocalDate.of(2018, 7, 19),
                null,
                0,
                "John Doe");
        nestBox1115.addNest(emptyNestBox);
    }
}
