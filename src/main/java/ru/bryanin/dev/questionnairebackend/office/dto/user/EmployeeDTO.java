package ru.bryanin.dev.questionnairebackend.office.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.bryanin.dev.questionnairebackend.office.entity.user.EmployeePosition;
import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;

@Data
public class EmployeeDTO {
    private Long id;
    private String email;
    private String password;
    private String securityRole;
    private String firstName;
    private String lastName;
    private Long companyId;
    private String companyTitle;
    private String phone;
    private AccessStatus accessStatus;
    private EmployeePosition position;
    private String positionTitle;

}
