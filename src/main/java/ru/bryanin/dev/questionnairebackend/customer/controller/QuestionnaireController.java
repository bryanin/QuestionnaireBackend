package ru.bryanin.dev.questionnairebackend.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bryanin.dev.questionnairebackend.customer.service.QuestionnaireService;

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
