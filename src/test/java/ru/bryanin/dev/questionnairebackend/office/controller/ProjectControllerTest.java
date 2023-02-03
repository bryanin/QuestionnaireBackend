package ru.bryanin.dev.questionnairebackend.office.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.with;

public class TestProjectControllerTest {

    public final String URI = "http://localhost:8080";
    public final String URL = "/api/v1/auth/login";
    public String jwt;

    @BeforeAll
    public void TestGetTokenTest() throws JsonProcessingException {
        if (jwt == null) {
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
            System.out.println(body);
            JsonNode jsonNode = new ObjectMapper().readValue(body, JsonNode.class);
            jwt = jsonNode.get("token").asText();
        }
        System.out.println(jwt);
        //return jwt;
    }


    @Test
    public void getRequestAndGet200OK() {
        RestAssured
                .when().get(URL)
                .then().assertThat().statusCode(200);
    }
}
