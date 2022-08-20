package ru.bryanin.dev.questionnairebackend.office.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Stage {
    SPECIFICATION("Спецификация"),
    ARRANGEMENT_OF_EQUIPMENT(""),
    STRUCTURAL_SCHEME(""),
    EXPLANATORY_NOTE(""),
    ACOUSTIC_CALCULATION(""),
    CONNECTION_SCHEME(""),
    POWER_SUPPLY_CALCULATION(""),
    AXONOMETRIC_SCHEME(""),
    CABLE_LOCATION_CABLE(""),
    PROJECT_DOCUMENTATION(""),
    WORKING_DOCUMENTATION("");

    private final String description;
}
