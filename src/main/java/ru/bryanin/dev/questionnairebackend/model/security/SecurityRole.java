package ru.bryanin.dev.questionnairebackend.model.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum SecurityRole {
    ADMIN(Sets.immutableEnumSet(
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_READ,
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_WRITE,
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_READ,
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_WRITE,
            SecurityPermission.HEAD_OF_DESIGN_DEPARTMENT_READ,
            SecurityPermission.HEAD_OF_DESIGN_DEPARTMENT_WRITE,
            SecurityPermission.SENIOR_DESIGNER_READ,
            SecurityPermission.SENIOR_DESIGNER_WRITE,
            SecurityPermission.MIDDLE_DESIGNER_READ,
            SecurityPermission.MIDDLE_DESIGNER_WRITE,
            SecurityPermission.JUNIOR_DESIGNER_READ,
            SecurityPermission.JUNIOR_DESIGNER_WRITE,
            SecurityPermission.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT_READ,
            SecurityPermission.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT_WRITE,
            SecurityPermission.SENIOR_ENGINEER_READ,
            SecurityPermission.SENIOR_ENGINEER_WRITE,
            SecurityPermission.MIDDLE_ENGINEER_READ,
            SecurityPermission.MIDDLE_ENGINEER_WRITE,
            SecurityPermission.JUNIOR_ENGINEER_READ,
            SecurityPermission.JUNIOR_ENGINEER_WRITE,
            SecurityPermission.HEAD_OF_SALES_READ,
            SecurityPermission.HEAD_OF_SALES_WRITE,
            SecurityPermission.SENIOR_SALES_MANAGER_READ,
            SecurityPermission.SENIOR_SALES_MANAGER_WRITE,
            SecurityPermission.MIDDLE_SALES_MANAGER_READ,
            SecurityPermission.MIDDLE_SALES_MANAGER_WRITE,
            SecurityPermission.JUNIOR_SALES_MANAGER_READ,
            SecurityPermission.JUNIOR_SALES_MANAGER_WRITE,
            SecurityPermission.CUSTOMER_READ,
            SecurityPermission.CUSTOMER_WRITE
    )),
    HEAD_OF_PROMOTION_DEPARTMENT(Sets.immutableEnumSet(
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_READ,
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_WRITE,
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_READ,
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_WRITE
            )),
    HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT(Sets.immutableEnumSet(
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_READ,
            SecurityPermission.HEAD_OF_PROMOTION_DEPARTMENT_ASSISTANT_WRITE
    )),
    HEAD_OF_DESIGN_DEPARTMENT(Sets.immutableEnumSet(
            SecurityPermission.HEAD_OF_DESIGN_DEPARTMENT_READ,
            SecurityPermission.HEAD_OF_DESIGN_DEPARTMENT_WRITE,
            SecurityPermission.SENIOR_DESIGNER_READ,
            SecurityPermission.SENIOR_DESIGNER_WRITE,
            SecurityPermission.MIDDLE_DESIGNER_READ,
            SecurityPermission.MIDDLE_DESIGNER_WRITE,
            SecurityPermission.JUNIOR_DESIGNER_READ,
            SecurityPermission.JUNIOR_DESIGNER_WRITE
            )),
    SENIOR_DESIGNER(Sets.immutableEnumSet(
            SecurityPermission.SENIOR_DESIGNER_READ,
            SecurityPermission.SENIOR_DESIGNER_WRITE,
            SecurityPermission.MIDDLE_DESIGNER_READ,
            SecurityPermission.MIDDLE_DESIGNER_WRITE,
            SecurityPermission.JUNIOR_DESIGNER_READ,
            SecurityPermission.JUNIOR_DESIGNER_WRITE
    )),
    MIDDLE_DESIGNER(Sets.immutableEnumSet(
            SecurityPermission.MIDDLE_DESIGNER_READ,
            SecurityPermission.MIDDLE_DESIGNER_WRITE,
            SecurityPermission.JUNIOR_DESIGNER_READ,
            SecurityPermission.JUNIOR_DESIGNER_WRITE
    )),
    JUNIOR_DESIGNER(Sets.immutableEnumSet(
            SecurityPermission.JUNIOR_DESIGNER_READ,
            SecurityPermission.JUNIOR_DESIGNER_WRITE
    )),
    HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT(Sets.immutableEnumSet(
            SecurityPermission.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT_READ,
            SecurityPermission.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT_WRITE,
            SecurityPermission.SENIOR_ENGINEER_READ,
            SecurityPermission.SENIOR_ENGINEER_WRITE,
            SecurityPermission.MIDDLE_ENGINEER_READ,
            SecurityPermission.MIDDLE_ENGINEER_WRITE,
            SecurityPermission.JUNIOR_ENGINEER_READ,
            SecurityPermission.JUNIOR_ENGINEER_WRITE
    )),
    SENIOR_ENGINEER(Sets.immutableEnumSet(
            SecurityPermission.SENIOR_ENGINEER_READ,
            SecurityPermission.SENIOR_ENGINEER_WRITE,
            SecurityPermission.MIDDLE_ENGINEER_READ,
            SecurityPermission.MIDDLE_ENGINEER_WRITE,
            SecurityPermission.JUNIOR_ENGINEER_READ,
            SecurityPermission.JUNIOR_ENGINEER_WRITE
    )),
    MIDDLE_ENGINEER(Sets.immutableEnumSet(
            SecurityPermission.MIDDLE_ENGINEER_READ,
            SecurityPermission.MIDDLE_ENGINEER_WRITE,
            SecurityPermission.JUNIOR_ENGINEER_READ,
            SecurityPermission.JUNIOR_ENGINEER_WRITE
    )),
    JUNIOR_ENGINEER(Sets.immutableEnumSet(
            SecurityPermission.JUNIOR_ENGINEER_READ,
            SecurityPermission.JUNIOR_ENGINEER_WRITE
    )),
    HEAD_OF_SALES(Sets.immutableEnumSet(
            SecurityPermission.HEAD_OF_SALES_READ,
            SecurityPermission.HEAD_OF_SALES_WRITE,
            SecurityPermission.SENIOR_SALES_MANAGER_READ,
            SecurityPermission.SENIOR_SALES_MANAGER_WRITE,
            SecurityPermission.MIDDLE_SALES_MANAGER_READ,
            SecurityPermission.MIDDLE_SALES_MANAGER_WRITE,
            SecurityPermission.JUNIOR_SALES_MANAGER_READ,
            SecurityPermission.JUNIOR_SALES_MANAGER_WRITE
    )),
    SENIOR_SALES_MANAGER(Sets.immutableEnumSet(
            SecurityPermission.SENIOR_SALES_MANAGER_READ,
            SecurityPermission.SENIOR_SALES_MANAGER_WRITE,
            SecurityPermission.MIDDLE_SALES_MANAGER_READ,
            SecurityPermission.MIDDLE_SALES_MANAGER_WRITE,
            SecurityPermission.JUNIOR_SALES_MANAGER_READ,
            SecurityPermission.JUNIOR_SALES_MANAGER_WRITE
    )),
    MIDDLE_SALES_MANAGER(Sets.immutableEnumSet(
            SecurityPermission.MIDDLE_SALES_MANAGER_READ,
            SecurityPermission.MIDDLE_SALES_MANAGER_WRITE,
            SecurityPermission.JUNIOR_SALES_MANAGER_READ,
            SecurityPermission.JUNIOR_SALES_MANAGER_WRITE
    )),
    JUNIOR_SALES_MANAGER(Sets.immutableEnumSet(
            SecurityPermission.JUNIOR_SALES_MANAGER_READ,
            SecurityPermission.JUNIOR_SALES_MANAGER_WRITE
    )),
    CUSTOMER(Sets.immutableEnumSet(
            SecurityPermission.CUSTOMER_READ,
            SecurityPermission.CUSTOMER_WRITE
    ));

    private final Set<SecurityPermission> securityPermissions;

    public Set<SecurityPermission> getPermissions() {
        return securityPermissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream().map(securityPermission -> new SimpleGrantedAuthority(securityPermission.getPermission())).collect(Collectors.toSet());
    }
}
