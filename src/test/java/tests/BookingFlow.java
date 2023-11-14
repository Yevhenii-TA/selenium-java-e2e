package tests;

import base.TestBaseLocal;
import org.example.pageobject_model.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookingFlow extends TestBaseLocal {

    @Test (invocationCount = 20, dataProvider = "locations")
    public void bookingFlow(String url)  {
        LandingPage landingPage = new LandingPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ExtrasPage extrasPage = new ExtrasPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        String expectedUrl = url.replace("/dates/", "/makebooking");

        landingPage.openHomePage(url)
                .acceptCookies()
                .fillEntryDates()
                .fillExitDates()
                .submitForm();
        productPage.selectRandomProduct();
        //extrasPage.selectRandomExtras();
        extrasPage.goToPayment();
        paymentPage.fillInMainFields()
                .fillInRequiredFields()
                .fillInCardData()
                .payNow()
                .confirmBooking()
                .waitUrl(expectedUrl);
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Booking was not completed");
    }

    @DataProvider(name = "locations")
    public Object[] getData() {
        String[] location = {"https://booking.man.test.sso.maginfrastructure.com/parking/dates/", "https://booking.stn.test.sso.maginfrastructure.com/parking/dates/"};
        return location;
    }
}
