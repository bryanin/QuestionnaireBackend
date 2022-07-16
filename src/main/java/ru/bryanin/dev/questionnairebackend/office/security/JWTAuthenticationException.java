package ru.bryanin.dev.questionnairebackend.office.security;

import io.jsonwebtoken.JwtException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JWTAuthenticationException extends JwtException {

    private HttpStatus httpStatus;

    public JWTAuthenticationException(String message) {
        super(message);
    }

    public JWTAuthenticationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
