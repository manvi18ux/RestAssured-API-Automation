package tests;

import dataproviders.BookingDataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import specifications.RequestSpec;

public class BookingDataDrivenTest {

    @Test(dataProvider = "bookingNames",
          dataProviderClass = BookingDataProvider.class)
    public void testBookingSearch(String firstname) {

        System.out.println("Searching bookings for: " + firstname);

        given()
            .spec(RequestSpec.getRequestSpec())
            .queryParam("firstname", firstname)

        .when()
            .get("/booking")

        .then()
            .statusCode(200)
            .log().body();
    }
}