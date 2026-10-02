package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;

public class BookingSchemaTest {

    @Test
    public void validateBookingSchema() {

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

        System.out.println("Booking ID for schema validation: " + bookingId);

        given()
            .spec(RequestSpec.getRequestSpec())
            .pathParam("id", bookingId)

        .when()
            .get("/booking/{id}")

        .then()
            .statusCode(200)
            .body(io.restassured.module.jsv.JsonSchemaValidator
                    .matchesJsonSchemaInClasspath(
                            "schemas/booking-schema.json"
                    ));
    }
}