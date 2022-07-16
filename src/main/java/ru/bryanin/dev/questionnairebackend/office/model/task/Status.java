package ru.bryanin.dev.questionnairebackend.office.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    IN_HEAP("Новая"),
    UNDER_INITIAL_REVIEW_BY_ENGINEER("На первичной проверке инженером"),
    UNDER_INITIAL_REVIEW_BY_HEAD_OF_DESIGN_DEPARTMENT("На проверке у руководителя проектного отдела"),
    IN_QUEUE("В очереди задач"),
    CHANGED_IN_QUEUE("Изменена после утверждения"),
    CHOSEN_BY_DESIGNER("Выбрана потенциальным исполнителем"),
    ASSIGNED_TO_PERFORMER("Назначена исполнителю"),
    UNDER_FINAL_REVIEW_BY_ENGINEER("Выполнена. На проверке у инженера"),
    UNDER_DISPUTE("Открыт спор"),
    DISPUTE_REJECTED("Спор отклонен"),
    UNDER_REVIEW_BY_CUSTOMER("На проверке у клиента"),
    ACCEPTED_BY_CUSTOMER("Согласована клиентом");

    private final String description;
}
