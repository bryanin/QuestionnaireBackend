package ru.bryanin.dev.questionnairebackend.office.entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    NEW("Новая"),
    TO_BE_COMPLETED_BY_CUSTOMER("Задача находится на заполнении опросного листа и проверке у клиента"),
    COMPLETED_BY_CUSTOMER("Опросный лист заполнен и проверен клиентом"),
    UNDER_INITIAL_REVIEW_BY_THE_ENGINEER("Задача на проверке у инженера перед отправкой в проектный отдел"),
    UNDER_REVIEW_BY_THE_HEAD_OF_THE_PROJECT_DEPARTMENT("Задача отправлена руководителю проектного отдела"),
    UNDER_REVIEW_BY_THE_ENGINEER("Задача на согласовании сроков у инженера после предложения руководителя проектного отдела"),
    REQUESTED_BY_THE_ENGINEER("Инженер запрашивает подтверждение замены на одну или несколько задач"),
    IN_QUEUE("Задача поставлена в очередь, срок выполнения и объем работы согласован"),
    IN_PIPELINE("Задача передана на исполнение проектироввщику"),
//    W(),
//    W(),
//    W(),
    ARCHIVED("Архивирована");

    private final String description;
}
