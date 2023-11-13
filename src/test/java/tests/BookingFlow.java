package tests;

import base.TestBaseLocal;
import org.example.pageobject_model.ExtrasPage;
import org.example.pageobject_model.PaymentPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.pageobject_model.LandingPage;
import org.example.pageobject_model.ProductPage;

public class BookingFlow extends TestBaseLocal {

    @Test (invocationCount = 10)
    public void bookingMAN() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ExtrasPage extrasPage = new ExtrasPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);

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
                .ConfirmationModalDisplayed();
    }
}
