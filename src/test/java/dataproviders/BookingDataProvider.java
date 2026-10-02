package dataproviders;

import org.testng.annotations.DataProvider;

public class BookingDataProvider {

    @DataProvider(name = "bookingNames")
    public Object[][] bookingNames() {

        return new Object[][] {
                {"Alice"},
                {"Bob"},
                {"Charlie"}
        };
    }
}