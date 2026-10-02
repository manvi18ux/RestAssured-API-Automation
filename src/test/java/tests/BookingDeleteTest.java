package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;
import utils.AuthUtils;

public class BookingDeleteTest {

    @Test
    public void deleteBooking() {

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

        System.out.println("Booking ID to delete: " + bookingId);

        // Step 2: Get authentication token
        String token = AuthUtils.getToken();

        // Step 3: Delete the booking
        Response deleteResponse =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .cookie("token", token)
                    .pathParam("id", bookingId)
                    .log().all()

                .when()
                    .delete("/booking/{id}")

                .then()
                    .statusCode(201)
                    .extract()
                    .response();

        System.out.println("Delete Status Code: "
                + deleteResponse.getStatusCode());

        // Step 4: Verify that the booking no longer exists
        given()
            .spec(RequestSpec.getRequestSpec())
            .pathParam("id", bookingId)

        .when()
            .get("/booking/{id}")

        .then()
            .statusCode(404);

        System.out.println(
                "Booking successfully deleted: " + bookingId
        );
    }
}