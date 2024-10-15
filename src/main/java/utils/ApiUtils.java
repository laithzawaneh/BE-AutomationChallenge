package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

    public static Response get(String endpoint) {
        return RestAssured
                .given()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response get(String endpoint, String token) {
        return RestAssured
                .given()
                .header("token",  token)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response post(String endpoint, String body, String token) {
        return RestAssured
                .given()
                .header("token", token)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}
