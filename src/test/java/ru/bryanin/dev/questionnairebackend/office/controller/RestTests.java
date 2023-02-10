package ru.bryanin.dev.questionnairebackend.office.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RestTests {

    public ProjectControllerTest projectControllerTest = new ProjectControllerTest();

    @Test
    @DisplayName("get all projects and assert that status code is 200")
    void getAllProjectsAndAssertThatStatusIsCode200() {
        projectControllerTest.getAllProjectsAndAssertThatStatusIsCode200();
    }

    @Test
    @DisplayName("get all projects and assert that JSON is valid")
    void getAllProjectsAndAssertThatJsonIsValid() {
        projectControllerTest.getAllProjectsAndAssertThatJsonIsValid();
    }

}
