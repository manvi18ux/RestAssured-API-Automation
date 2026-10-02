package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Booking;
import models.BookingDates;
import org.testng.annotations.Test;

public class SerializationTest {

    @Test
    public void serializeBooking() throws Exception {

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

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(booking);

        System.out.println("Serialized JSON:");
        System.out.println(json);
    }
}