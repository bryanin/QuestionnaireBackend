package ru.bryanin.dev.questionnairebackend.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bryanin.dev.questionnairebackend.customer.service.QuestionnaireService;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;
import ru.bryanin.dev.questionnairebackend.office.service.TaskService;

@RestController
@RequestMapping("/customer")
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping("/questionnaire/{id}")
    public String getQuestionnaireByTaskId(@PathVariable Long id) {
        return questionnaireService.getQuestionnaireByTaskId(id);
    }

}
