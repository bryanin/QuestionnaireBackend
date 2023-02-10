package ru.bryanin.dev.questionnairebackend.office.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

@Component
public class ProjectControllerTest {

    public String URI;
    public String token;

    public ProjectControllerTest() {
        this.URI = "http://localhost:8080";
        this.token = null;
    }

    public String getToken() {
        String URL = "/api/v1/auth/login";
        if (token == null) {
            String loginPasswordJson = "{\"email\": \"" + "kondrich.anastasiya@luis.ru" + "\", \"password\": \"" + "123" + "\" }";
            String body = with()
                    .contentType(ContentType.JSON)
                    .body(loginPasswordJson)
                    .baseUri(URI)
                    .post(URL)
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();
            try {
                JsonNode jsonNode = new ObjectMapper().readValue(body, JsonNode.class);
                token = jsonNode.get("token").asText();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
        return token;
    }

    public void getAllProjectsAndAssertThatStatusIsCode200() {
        String URL = "/api/v1/project";
        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .baseUri(URI)
                .header("Authorization", getToken())
                .get(URL)
                .then()
                .statusCode(200);
    }

    public void getAllProjectsAndAssertThatJsonIsValid() {
//        String URL = "/api/v1/project";
//        given()
//                .with()
//                .contentType(ContentType.JSON)
//                .baseUri(URI)
//                .header("Authorization", getToken())
//                .get(URL)
//                .then()
//                .body("ownerEmail", a)
    }
}
