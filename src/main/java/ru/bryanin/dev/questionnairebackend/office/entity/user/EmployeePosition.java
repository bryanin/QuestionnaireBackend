package ru.bryanin.dev.questionnairebackend.office.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeePosition {

    HEAD_OF_PROMOTION_DEPARTMENT("Руководитель отдела продвижения"),
    HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT("Помощник руководителя отдела продвижения"),
    HEAD_OF_DESIGN_DEPARTMENT("Руководитель отдела проектирования"),
    SENIOR_DESIGNER("Старший проектировщик"),
    MIDDLE_DESIGNER("Проектировщик"),
    JUNIOR_DESIGNER("Младший проектировщик"),
    HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT("Руководитель отдела по работе с проектировщиками"),
    SENIOR_ENGINEER("Старший инженер по продвижению"),
    MIDDLE_ENGINEER("Инженер по продвижению"),
    JUNIOR_ENGINEER("Младший инженер по продвижению"),
    HEAD_OF_SALES("Руководитель отдела продаж"),
    SENIOR_SALES_MANAGER("Старший менеджер по продажам"),
    MIDDLE_SALES_MANAGER("Менеджер по продажам"),
    JUNIOR_SALES_MANAGER("Младший менеджер по продажам");

    private String title;

}
