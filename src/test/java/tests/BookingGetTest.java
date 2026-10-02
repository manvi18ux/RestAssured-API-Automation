package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import specifications.RequestSpec;
import specifications.ResponseSpec;

public class BookingGetTest {

    @Test
    public void getBookingIds() {

        given()
            .spec(RequestSpec.getRequestSpec())

        .when()
            .get("/booking")

        .then()
            .spec(ResponseSpec.getResponseSpec())
            .statusCode(200)
            .log().body();
    }
}