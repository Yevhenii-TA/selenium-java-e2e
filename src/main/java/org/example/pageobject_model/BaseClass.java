package org.example.pageobject_model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BaseClass {
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected WebDriverWait wait;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    public BaseClass waitHandlerClickable (WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }
    public BaseClass waitElementVisible (WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return this;
    }
    public BaseClass waitPageLoaded() {
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        return this;
    }
    public BaseClass waitElementDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
        return this;
    }
    public BaseClass waitUrl(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
        return this;
    }

}
