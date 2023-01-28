package ru.bryanin.dev.questionnairebackend.office.dto.task;

import lombok.*;
import ru.bryanin.dev.questionnairebackend.office.entity.task.*;
import java.util.Set;

@Data
public class TaskDTO {

    private Long id;
    private String ownerEmail;
    private String ownerLastName;
    private String ownerFirstName;
    private Status status;
    private String statusDescription;
    private Complexity complexity;
    private String complexityDescription;
    private String securitySystem;
    private String securitySystemDescription;
    private Long projectId;
    private String projectTitle;
    private String questionnaire;
    private String performerEmail;
    private String performerLastName;
    private String performerFirstName;
    private Set<Stage> stageSet;
    private Set<String> stageSetDescription;
    private String createdAt;
    private String guaranteedDueDate;
    private String desiredDueDate;
    private String actualLaunchDate;
    private String estimatedCompletionDate;
    private String dateOfActualCompletion;

}
