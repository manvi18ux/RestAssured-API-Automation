package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Booking;
import org.testng.annotations.Test;

public class DeserializationTest {

    @Test
    public void deserializeBooking() throws Exception {

        String json =
                "{\"firstname\":\"John\",\"lastname\":\"Doe\",\"totalprice\":500,\"depositpaid\":true}";

        ObjectMapper objectMapper = new ObjectMapper();

        Booking booking =
                objectMapper.readValue(json, Booking.class);

        System.out.println("First Name: " + booking.getFirstname());
        System.out.println("Last Name: " + booking.getLastname());
        System.out.println("Total Price: " + booking.getTotalprice());
        System.out.println("Deposit Paid: " + booking.isDepositpaid());
    }
}