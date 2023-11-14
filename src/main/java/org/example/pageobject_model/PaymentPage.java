package org.example.pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Random;

public class PaymentPage extends BaseClass {
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='EmailAddress']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='ConfirmEmailAddress']")
    private WebElement confirmEmailField;
    @FindBy(xpath = "//*[@id='VehicleDetails_Registration']")
    private WebElement vehicleRegistration;
    @FindBy(xpath = "//div[@class='input']//*[@id='qa-find-vehicle-button']")
    private WebElement findVehicleButton;
    @FindBy(xpath = "//*[@id='VehicleDetails_Make']")
    private WebElement vehicleMake;
    @FindBy(xpath = "//*[@id='VehicleDetails_Model']")
    private WebElement vehicleModel;
    @FindBy(xpath = "//*[@id='VehicleDetails_Colour']")
    private WebElement vehicleColour;
    @FindBy(css = "#credit-card-number")
    private WebElement cardNumber;
    @FindBy(css = "#expiration")
    private WebElement cardExpire;
    @FindBy(css = "#cvv")
    private WebElement cardCVV;
    @FindBy(xpath = "//a[contains(text(),'Pay Now')]")
    private WebElement payNow;
    @FindBy(xpath = "//*[@id='Cardinal-Modal']")
    private WebElement confirmModal;
    @FindBy(xpath = "//*[@name='challengeDataEntry']")
    private WebElement confirmModalInput;
    @FindBy(xpath = "//*[@value='SUBMIT']")
    private WebElement confirmModalSubmit;
    @FindBy(xpath = "//*[@class='confirmation-message--success']")
    private WebElement bookingConfirmationMessage;
    @FindBy(xpath = "//*[@class='modal-close js-modal-close']")
    private WebElement bookingConfirmationModalClose;
    @FindBy(xpath = "//button[contains(@class,'SurveyInvitation__closeButton')]")
    private WebElement invitationModalClose;
    @FindBy(xpath = "//*[@class='button button--primary button--arrow button--loading']")
    private WebElement payNowLoadingButton;

    public int createRandomNumber() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        return random.ints(min, max).findFirst().getAsInt();
    }
    public PaymentPage fillInRequiredFields() {
        int id = createRandomNumber();
        List<WebElement> requiredField = driver.findElements(By.xpath("//*[@data-validmag-required='true' and not(contains(@type,'email')) and not(contains(@id,'Vehicle'))]"));
        if (requiredField.size() > 0) {
            for (WebElement element : requiredField) {
                element.sendKeys("booking" + id);
            }
        }
        return this;
    }
    public PaymentPage fillInMainFields() {
        int id = createRandomNumber();
        waitElementVisible(emailField);
        emailField.sendKeys("booking" + id + "@mag.test");
        confirmEmailField.sendKeys("booking" + id + "@mag.test");
        vehicleRegistration.sendKeys("V" + id);
        findVehicleButton.click();
        waitElementVisible(vehicleMake);
        vehicleMake.sendKeys("Make-" + id);
        vehicleModel.sendKeys("Model-" + id);
        vehicleColour.sendKeys("Red-" + id);
        return this;
    }
    public PaymentPage fillInCardData()  {
        driver.switchTo().frame("braintree-hosted-field-number");
        waitElementVisible(cardNumber);
        cardNumber.sendKeys("4111111111111111");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("braintree-hosted-field-expirationDate");
        waitElementVisible(cardExpire);
        cardExpire.sendKeys("1228");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("braintree-hosted-field-cvv");
        waitElementVisible(cardCVV);
        cardCVV.sendKeys("123");
        driver.switchTo().defaultContent();
        return this;
    }
    public PaymentPage payNow() {
        waitHandlerClickable(payNow);
        payNow.click();
        return this;
    }

    public PaymentPage confirmBooking() {
        waitElementVisible(confirmModal);
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        waitHandlerClickable(confirmModalSubmit);
        confirmModalInput.sendKeys("1234");
        confirmModalSubmit.click();
        waitElementDisappear(confirmModal);
        return this;
    }

    public PaymentPage closeConfirmationModal() {
        waitElementVisible(bookingConfirmationModalClose);
        bookingConfirmationModalClose.click();
        return this;
    }
    public PaymentPage ConfirmationModalDisplayed() {
        waitElementVisible(bookingConfirmationModalClose);
        return this;
    }
    public PaymentPage closeInvitationModal() {
        waitElementVisible(invitationModalClose);
        invitationModalClose.click();
        return this;
    }
    public boolean isBookingCompleted() {
        waitElementVisible(bookingConfirmationMessage);
        return bookingConfirmationMessage.isDisplayed();
    }
}
