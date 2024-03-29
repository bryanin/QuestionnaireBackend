package ru.bryanin.dev.questionnairebackend.office.entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecuritySystem {
    SOT("Система видеонаблюдения"),
    SPS ("Система пожарной сигнализации"),
    APPZ("Автоматика противопожарной защиты"),
    AUPT("Автоматическая установка пожаротушения"),
    SOUE("Система оповещения и управления эвакуацией"),
    SOTS("Система охранно-тревожной сигнализации"),
    SKUD("Система контроля и управления доступом");

    private final String description;
}
