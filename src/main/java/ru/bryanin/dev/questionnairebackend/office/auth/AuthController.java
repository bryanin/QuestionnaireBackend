package ru.bryanin.dev.questionnairebackend.office.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class AuthController {

    @GetMapping(path = "/auth/login")
    public String loginPage() {
        return "login";
    }

}
