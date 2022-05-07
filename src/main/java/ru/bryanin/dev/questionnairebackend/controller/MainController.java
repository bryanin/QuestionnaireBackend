package ru.bryanin.dev.questionnairebackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MainController {

    @GetMapping(path = "/auth/login")
    public String loginPage() {
        return "login";
    }

}
