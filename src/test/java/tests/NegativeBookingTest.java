package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;

public class NegativeBookingTest {

    @Test
    public void getInvalidBooking() {

        int invalidBookingId = 999999999;

        System.out.println(
                "Testing invalid Booking ID: " + invalidBookingId
        );

        given()
            .spec(RequestSpec.getRequestSpec())
            .pathParam("id", invalidBookingId)

        .when()
            .get("/booking/{id}")

        .then()
            .statusCode(404);
    }
}