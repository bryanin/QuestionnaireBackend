package ru.bryanin.dev.questionnairebackend.office.entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Stage {
    SPECIFICATION("Спецификация"),
    ARRANGEMENT_OF_EQUIPMENT("Расстанововка на планах"),
    STRUCTURAL_SCHEME("Структурная схема"),
    EXPLANATORY_NOTE("Пояснительная записка"),
    ACOUSTIC_CALCULATION("Акустический расчет"),
    CONNECTION_SCHEME("Схема подключений"),
    POWER_SUPPLY_CALCULATION("Расчет источников электропитания"),
    AXONOMETRIC_SCHEME("Аксонометрическая схема"),
    CABLE_LOCATION_CABLE("Кабельный журнал"),
    PROJECT_DOCUMENTATION("Стадия П"),
    WORKING_DOCUMENTATION("Стадия Р");

    private final String description;
}
