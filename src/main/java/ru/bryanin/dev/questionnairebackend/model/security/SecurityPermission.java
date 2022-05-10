package ru.bryanin.dev.questionnairebackend.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityPermission {
    HEAD_OF_PROMOTION_DEPARTMENT_READ("head_of_promotion_department:read"),
    HEAD_OF_PROMOTION_DEPARTMENT_WRITE("head_of_promotion_department:write"),
    HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_READ("head_of_promotion_department_assistant:read"),
    HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_WRITE("head_of_promotion_department_assistant:write"),
    HEAD_OF_DESIGN_DEPARTMENT_READ("head_of_design_department:read"),
    HEAD_OF_DESIGN_DEPARTMENT_WRITE("head_of_design_department:write"),
    SENIOR_DESIGNER_READ("senior_designer:read"),
    SENIOR_DESIGNER_WRITE("senior_designer:write"),
    MIDDLE_DESIGNER_READ("middle_designer:read"),
    MIDDLE_DESIGNER_WRITE("middle_designer:write"),
    JUNIOR_DESIGNER_READ("junior_designer:read"),
    JUNIOR_DESIGNER_WRITE("junior_designer:write"),
    HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT_READ("head_of_engineer_promotion_department:read"),
    HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT_WRITE("head_of_engineer_promotion_department:write"),
    SENIOR_ENGINEER_READ("senior_engineer:read"),
    SENIOR_ENGINEER_WRITE("senior_engineer:write"),
    MIDDLE_ENGINEER_READ("middle_engineer:read"),
    MIDDLE_ENGINEER_WRITE("middle_engineer:write"),
    JUNIOR_ENGINEER_READ("junior_engineer:read"),
    JUNIOR_ENGINEER_WRITE("junior_engineer:write"),
    HEAD_OF_SALES_READ("head_of_sales:read"),
    HEAD_OF_SALES_WRITE("head_of_sales:write"),
    SENIOR_SALES_MANAGER_READ("senior_sales_manager:read"),
    SENIOR_SALES_MANAGER_WRITE("senior_sales_manager:write"),
    MIDDLE_SALES_MANAGER_READ("middle_sales_manager:read"),
    MIDDLE_SALES_MANAGER_WRITE("middle_sales_manager:write"),
    JUNIOR_SALES_MANAGER_READ("junior_sales_manager:read"),
    JUNIOR_SALES_MANAGER_WRITE("junior_sales_manager:write"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write");

    private final String permission;

}
