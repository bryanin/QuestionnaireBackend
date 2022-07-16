package ru.bryanin.dev.questionnairebackend.office.auth;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}
