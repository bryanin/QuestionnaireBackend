package ru.bryanin.dev.questionnairebackend.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum TaskComplexity {
    LEVEL_1("*"),
    LEVEL_2("**"),
    LEVEL_3("***"),
    LEVEL_4("****"),
    LEVEL_5("*****");

    private String description;
}
