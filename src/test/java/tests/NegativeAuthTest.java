package tests;

import io.restassured.response.Response;
import models.Booking;
import models.BookingDates;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;

public class NegativeAuthTest {

    @Test
    public void updateBookingWithInvalidToken() {

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

        System.out.println("Booking ID: " + bookingId);

        // Step 2: Create update data
        BookingDates dates = new BookingDates(
                "2026-12-01",
                "2026-12-05"
        );

        Booking updatedBooking = new Booking(
                "InvalidAuth",
                "Test",
                100,
                true,
                dates,
                "Test"
        );

        // Step 3: Send PUT with invalid token
        given()
            .spec(RequestSpec.getRequestSpec())
            .contentType("application/json")
            .cookie("token", "invalid-token-12345")
            .pathParam("id", bookingId)
            .body(updatedBooking)

        .when()
            .put("/booking/{id}")

        .then()
            .statusCode(403);
    }
}