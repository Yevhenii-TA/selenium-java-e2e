package org.example.pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class ExtrasPage extends BaseClass {
    public ExtrasPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//*[contains(@id,'stepper-plus')])[1]")
    private WebElement anchorWait;
    @FindBy(xpath = "//*[@id='qa-continue-to-payment' and contains(text(),'Continue to Payment')]")
    private WebElement goToPaymentButton;

    public int getRandomSelectButton(int min, int max) {
        Random random = new Random();
        if (max == 1) {
            return 1;
        } else {
            return random.ints(min, max).findFirst().getAsInt();
        }
    }

    public ExtrasPage selectRandomExtras() {
        waitHandlerClickable(anchorWait);
        int maxQtyButtons = driver.findElements(By.xpath("//*[contains(@id,'stepper-plus')]")).size();
        int value = getRandomSelectButton(1, maxQtyButtons);
        WebElement extraByNumber = driver.findElement(By.xpath("(//*[contains(@id,'stepper-plus')])[" + value + "]"));
        System.out.println(value);
        extraByNumber.click();
        return this;
    }
    public PaymentPage goToPayment() throws InterruptedException {
        waitHandlerClickable(goToPaymentButton);
        Thread.sleep(5000);
        if(goToPaymentButton.isDisplayed()) {
            goToPaymentButton.click();
        }
        return new PaymentPage(driver);
    }

}