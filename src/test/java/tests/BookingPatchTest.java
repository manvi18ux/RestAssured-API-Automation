package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import specifications.RequestSpec;
import utils.AuthUtils;

public class BookingPatchTest {

    @Test
    public void patchBooking() {

        // Step 1: Get a valid booking ID
        Response bookingList =
                given()
                    .spec(RequestSpec.getRequestSpec())

                .when()
                    .get("/booking")

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        int bookingId =
                bookingList.jsonPath().getInt("[0].bookingid");

        System.out.println("Booking ID to patch: " + bookingId);

        // Step 2: Get authentication token
        String token = AuthUtils.getToken();

        // Step 3: Create partial update
        String patchBody =
                "{"
                + "\"firstname\":\"ManviPatch\""
                + "}";

        // Step 4: Send PATCH request
        given()
            .spec(RequestSpec.getRequestSpec())
            .contentType("application/json")
            .cookie("token", token)
            .pathParam("id", bookingId)
            .body(patchBody)
            .log().all()

        .when()
            .patch("/booking/{id}")

        .then()
            .statusCode(200)
            .body("firstname", equalTo("ManviPatch"));
    }
}