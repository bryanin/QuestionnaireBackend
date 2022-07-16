package ru.bryanin.dev.questionnairebackend.office.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/questionnaire")
public class QuestionnaireController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:read', " +
            "'head_of_promotion_department_assistant:read'," +
            "'head_of_design_department:read'," +
            "'senior_designer:read'," +
            "'middle_designer:read'," +
            "'junior_designer:read'," +
            "'head_of_engineer_promotion_department:read'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:read'," +
            "'senior_sales_manager:read'," +
            "'middle_sales_manager:read'," +
            "'junior_sales_manager:read')")
    public List<String> getAllQuestionnaires() throws Exception {
        String appz = new String(Files.readAllBytes(Paths.get("src/main/resources/questionnaires/APPZ.json")), StandardCharsets.UTF_8);
        String aupt = new String(Files.readAllBytes(Paths.get("src/main/resources/questionnaires/AUPT.json")), StandardCharsets.UTF_8);
        String skud = new String(Files.readAllBytes(Paths.get("src/main/resources/questionnaires/SKUD.json")), StandardCharsets.UTF_8);
        String sot = new String(Files.readAllBytes(Paths.get("src/main/resources/questionnaires/SOT.json")), StandardCharsets.UTF_8);
        String sots = new String(Files.readAllBytes(Paths.get("src/main/resources/questionnaires/SOTS.json")), StandardCharsets.UTF_8);
        String soue = new String(Files.readAllBytes(Paths.get("src/main/resources/questionnaires/SOUE.json")), StandardCharsets.UTF_8);
        String sps = new String(Files.readAllBytes(Paths.get("src/main/resources/questionnaires/SPS.json")), StandardCharsets.UTF_8);

        return new ArrayList<>(Arrays.asList(appz, aupt, skud, sot, sots, soue, sps));
    }
}
