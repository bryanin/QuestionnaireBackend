package ru.bryanin.dev.questionnairebackend.auth;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}
