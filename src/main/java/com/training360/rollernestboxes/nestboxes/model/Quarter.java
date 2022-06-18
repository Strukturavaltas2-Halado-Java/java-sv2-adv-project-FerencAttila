package com.training360.rollernestboxes.nestboxes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Quarter {

    N("North"),
    NE("Northeast"),
    E("East"),
    SE("Southeast"),
    S("South"),
    SW("Southwest"),
    W("West"),
    NW("Northwest");

    private final String description;
}
