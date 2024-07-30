package org.example.pageobject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class ProductPage extends BaseClass {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//*[@class='product__additional-buttons']/span/span/a)[1]")
    private WebElement anchorWait;

    public int getRandomSelectButton(int min, int max) {
        Random random = new Random();
        if (max == 1) {
            return 1;
        } else {
            return random.ints(min, max).findFirst().getAsInt();
        }
    }

    public ExtrasPage selectRandomProduct() {
        waitHandlerClickable(anchorWait);
        int maxQtyButtons = driver.findElements(By.xpath("//*[@class='product__additional-buttons']/span/span/a")).size();
        int value = getRandomSelectButton(1,maxQtyButtons);
        WebElement productByNumber = driver.findElement(By.xpath("(//*[@class='product__additional-buttons']/span/span/a)["+ value +"]"));
        System.out.println(value);
        productByNumber.click();
        return new ExtrasPage(driver);
    }

    public ExtrasPage selectProductBne() {
        waitHandlerClickable(anchorWait);
        WebElement productBne = driver.findElement(By.xpath("(//*[@class='product__additional-buttons']/span/span/a)[3]"));
        productBne.click();
        return new ExtrasPage(driver);
    }
}
