package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import specifications.RequestSpec;

public class BookingByIdTest {

    @Test
    public void getBookingById() {

        Response response =
                given()
                    .spec(RequestSpec.getRequestSpec())

                .when()
                    .get("/booking")

                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        int bookingId =
                response.jsonPath().getInt("[0].bookingid");

        System.out.println("Booking ID selected: " + bookingId);

        given()
            .spec(RequestSpec.getRequestSpec())
            .pathParam("id", bookingId)

        .when()
            .get("/booking/{id}")

        .then()
            .statusCode(200)
            .body("firstname", notNullValue())
            .body("lastname", notNullValue());
    }
}