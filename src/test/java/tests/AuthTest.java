package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;

public class AuthTest {

    @Test
    public void createAuthToken() {

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

        String token =
                response.jsonPath().getString("token");

        System.out.println("Authentication Token: " + token);

        Assert.assertNotNull(token);
        Assert.assertFalse(token.isEmpty());
    }
}