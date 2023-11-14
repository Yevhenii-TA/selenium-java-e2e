package tests;

import base.TestBaseLocal;
import org.example.pageobject_model.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingFlow extends TestBaseLocal {

    @Test (invocationCount = 20)
    public void bookingMAN()  {
        LandingPage landingPage = new LandingPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ExtrasPage extrasPage = new ExtrasPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);
        String expectedUrl = "https://booking.man.test.sso.maginfrastructure.com/parking/makebooking";

        landingPage.openHomePage()
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
}
