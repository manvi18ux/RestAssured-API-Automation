package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;

public class AuthUtils {

    public static String getToken() {

        String requestBody =
                "{"
                + "\"username\":\"admin\","
                + "\"password\":\"password123\""
                + "}";

        Response response =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .contentType("application/json")
                    .body(requestBody)

                .when()
                    .post("/auth")

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        return response.jsonPath().getString("token");
    }
}