package ru.bryanin.dev.questionnairebackend.office.entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Complexity {
    LEVEL_1("*"),
    LEVEL_2("**"),
    LEVEL_3("***"),
    LEVEL_4("****"),
    LEVEL_5("*****");

    private String description;
}
