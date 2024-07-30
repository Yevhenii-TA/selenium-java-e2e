package tests;

import base.TestBaseLocal;
import org.example.pageobject_model.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookingFlow extends TestBaseLocal {

    private static final String HOMEPAGE_MAN = "https://booking.man.test.sso.maginfrastructure.com/parking/dates/";
    private static final String BOI_HOMEPAGE = "https://booking.boi-sandbox.mago-int.com/parking/dates/";
    private static final String BNE_HOMEPAGE = "https://bac-sandbox.web.magr.es/";
    PaymentPage paymentPage = new PaymentPage(driver);
    @Test //(invocationCount = 20) //dataProvider = "locations"
    public void bookingFlow() throws InterruptedException {
        String expectedUrl = HOMEPAGE_MAN.replace("/dates/", "/makebooking");

        new LandingPage(driver)
                .openHomePage(HOMEPAGE_MAN)
                .acceptCookies()
                .fillEntryDates()
                .fillExitDates()
                .submitForm()
                .selectRandomProduct()
                .goToPayment()
                .fillInMainFields()
                .fillInRequiredFields()
                .fillInCardData()
                .payNow()
                .confirmBooking()
                .waitUrl(expectedUrl);
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Booking was not completed");
    }

    @Test (invocationCount = 10)
    public void bookingFlowBOI() throws InterruptedException {
        new LandingPage(driver)
                .openHomePage(BOI_HOMEPAGE)
                .acceptCookies()
                .fillEntryDates()
                .fillExitDates()
                .submitForm()
                .selectRandomProduct()
                .goToPayment()
                .fillInMainFields()
                .fillInRequiredFields()
                .fillInCardDataBoi()
                .selectAgreement()
                .payNowBoi();
        Assert.assertEquals(paymentPage.isBookingCompleted(), true, "Booking was not completed");
    }

    @Test (invocationCount = 10)
    public void bookingFlowBNE() throws InterruptedException {
        new LandingPage(driver)
                .openHomePage(BNE_HOMEPAGE)
                .acceptCookies()
                .fillEntryDates()
                .fillExitDates()
                .selectTerminal()
                .submitForm()
                .selectProductBne()
                .goToPayment()
                .fillInMainFields()
                .fillInRequiredFields()
                .fillInCardDataBne()
                .selectAgreement()
                .payNowBne();
        Assert.assertEquals(paymentPage.isBookingCompleted(), true, "Booking was not completed");
    }

    @DataProvider(name = "locations")
    public Object[] getData() {
        String[] location = {"https://booking.man.test.sso.maginfrastructure.com/parking/dates/", "https://booking.stn.test.sso.maginfrastructure.com/parking/dates/"};
        return location;
    }
}
