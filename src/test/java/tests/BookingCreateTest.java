package tests;

import io.restassured.response.Response;
import models.Booking;
import models.BookingDates;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;

public class BookingCreateTest {

    @Test
    public void createBooking() {

        BookingDates dates = new BookingDates(
                "2026-10-10",
                "2026-10-15"
        );

        Booking booking = new Booking(
                "Manvi",
                "Test",
                100,
                true,
                dates,
                "Breakfast"
        );

        Response response =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .contentType("application/json")
                    .body(booking)
                    .log().all()

                .when()
                    .post("/booking")

                .then()
                    .extract()
                    .response();

        System.out.println("Status Code: " + response.getStatusCode());

        System.out.println("Response Body:");
        response.prettyPrint();
    }
}