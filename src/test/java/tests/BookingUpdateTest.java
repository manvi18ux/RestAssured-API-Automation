package tests;

import io.restassured.response.Response;
import models.Booking;
import models.BookingDates;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import specifications.RequestSpec;
import utils.AuthUtils;

public class BookingUpdateTest {

    @Test
    public void updateBooking() {

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

        System.out.println("Booking ID to update: " + bookingId);

        // Step 2: Verify that the booking exists
        Response existingBooking =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .pathParam("id", bookingId)

                .when()
                    .get("/booking/{id}")

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        System.out.println("Existing Booking:");
        existingBooking.prettyPrint();

        // Step 3: Get authentication token
        String token = AuthUtils.getToken();

        // Step 4: Create updated booking
        BookingDates dates = new BookingDates(
                "2026-11-01",
                "2026-11-05"
        );

        Booking updatedBooking = new Booking(
                "ManviUpdated",
                "TestUpdated",
                500,
                true,
                dates,
                "Lunch"
        );

        // Step 5: Send PUT and validate response
        given()
            .spec(RequestSpec.getRequestSpec())
            .contentType("application/json")
            .cookie("token", token)
            .pathParam("id", bookingId)
            .body(updatedBooking)
            .log().all()

        .when()
            .put("/booking/{id}")

        .then()
            .statusCode(200)
            .body("firstname", equalTo("ManviUpdated"))
            .body("lastname", equalTo("TestUpdated"))
            .body("totalprice", equalTo(500))
            .body("depositpaid", equalTo(true))
            .body("bookingdates.checkin", equalTo("2026-11-01"))
            .body("bookingdates.checkout", equalTo("2026-11-05"))
            .body("additionalneeds", equalTo("Lunch"));
    }
}