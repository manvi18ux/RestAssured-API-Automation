package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import specifications.RequestSpec;

public class BookingQueryTest {

    @Test
    public void getBookingUsingQueryParameter() {

        // Step 1: Get available booking IDs
        Response bookingList =
                given()
                    .spec(RequestSpec.getRequestSpec())

                .when()
                    .get("/booking")

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        // Step 2: Extract a real booking ID
        int bookingId =
                bookingList.jsonPath().getInt("[0].bookingid");

        System.out.println("Booking ID: " + bookingId);

        // Step 3: Get the details of that booking
        Response bookingDetails =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .pathParam("id", bookingId)

                .when()
                    .get("/booking/{id}")

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        // Step 4: Extract firstname
        String firstName =
                bookingDetails.jsonPath().getString("firstname");

        System.out.println("First Name: " + firstName);

        // Step 5: Use firstname as a query parameter
        given()
            .spec(RequestSpec.getRequestSpec())
            .queryParam("firstname", firstName)

        .when()
            .get("/booking")

        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }
}