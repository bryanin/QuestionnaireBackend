package ru.bryanin.dev.questionnairebackend.office.dto.project;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Address;

@Data
public class ProjectDTO {

    private Long id;
    private String id_1C;
    private String title;
    private String description;
    private String ownerEmail;
    private String ownerLastName;
    private String ownerFirstName;
    private Address address;
    private String addressToString;
    private String createdAt;
    private String status;

}
