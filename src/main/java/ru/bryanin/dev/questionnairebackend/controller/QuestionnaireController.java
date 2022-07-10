package ru.bryanin.dev.questionnairebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bryanin.dev.questionnairebackend.model.questionnaire.Questionnaire;
//import ru.bryanin.dev.questionnairebackend.service.QuestionnaireService;

import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/questionnaire")
//public class QuestionnaireController {
//
//    private final QuestionnaireService questionnaireService;
//
//    public QuestionnaireController(QuestionnaireService questionnaireService) {
//        this.questionnaireService = questionnaireService;
//    }
//
//
//    @GetMapping()
//    @PreAuthorize("hasAnyAuthority(" +
//            "'head_of_promotion_department:read', " +
//            "'head_of_promotion_department_assistant:read'," +
//            "'head_of_design_department:read'," +
//            "'senior_designer:read'," +
//            "'middle_designer:read'," +
//            "'junior_designer:read'," +
//            "'head_of_engineer_promotion_department:read'," +
//            "'senior_engineer:read'," +
//            "'middle_engineer:read'," +
//            "'junior_engineer:read'," +
//            "'head_of_sales:read'," +
//            "'senior_sales_manager:read'," +
//            "'middle_sales_manager:read'," +
//            "'junior_sales_manager:read')")
//    public List<Questionnaire> getAllQuestionnaires() {
//        return questionnaireService.getAllQuestionnaires();
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority(" +
//            "'head_of_promotion_department:read', " +
//            "'head_of_promotion_department_assistant:read'," +
//            "'head_of_design_department:read'," +
//            "'senior_designer:read'," +
//            "'middle_designer:read'," +
//            "'junior_designer:read'," +
//            "'head_of_engineer_promotion_department:read'," +
//            "'senior_engineer:read'," +
//            "'middle_engineer:read'," +
//            "'junior_engineer:read'," +
//            "'head_of_sales:read'," +
//            "'senior_sales_manager:read'," +
//            "'middle_sales_manager:read'," +
//            "'junior_sales_manager:read')")
//    public ResponseEntity<Questionnaire> getQuestionnaire(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(questionnaireService.getQuestionnaire(id));
//    }
//}
