package org.example.pageobject_model;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BaseClass {

    public LandingPage(WebDriver driver) {
        super(driver);
    }
    private static final String HOMEPAGE_URL = "https://booking.man.test.sso.maginfrastructure.com/parking/dates/";  //made config and add this URL

    //region Selectors
    @FindBy(xpath = "//*[@id='entryDate']")
    private WebElement entryDateButton;
    @FindBy(xpath = "//*[@id='EntryTime']")
    private WebElement entryTimeButton;
    @FindBy(xpath = "//*[@id='exitDate']")
    private WebElement exitDateButton;
    @FindBy(xpath = "//*[@id='ExitTime']")
    private WebElement exitTimeButton;
    @FindBy(xpath = "//*[@class='form-date-widget']/div[1]//td[@class='is-today']/button")
    private WebElement selectEntryDateToday;
    @FindBy(xpath = "(//*[@class='form-date-widget']/div[1]//td[@class='']/button)[2]")
    private WebElement selectEntryDate2Day;
    @FindBy(xpath = "(//*[@id='EntryTime']/option)[16]")
    private WebElement selectEntryTime;
    @FindBy(xpath = "(//*[@class='form-date-widget']/div[2]//td[@class='']/button)[last()]")
    private WebElement selectExitDateLast;
    @FindBy(xpath = "(//*[@class='form-date-widget']/div[2]//td[@class='']/button)[5]")
    private WebElement selectExitDate5Day;
    @FindBy(xpath = "(//*[@class='form-date-widget']/div[2]//td[@class='']/button)[5]")
    private WebElement selectExitDate;
    @FindBy(xpath = "(//*[@id='ExitTime']/option)[16]")
    private WebElement selectExitTime;
    @FindBy(id = "NumberOfPassengers")
    private WebElement numberOfPassengers;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement submitForm;
    @FindBy(xpath = "//*[@id='cookie-accept']")
    private WebElement acceptCookie;
    @FindBy(xpath = "//*[@id='chosenTerminal']")
    private WebElement terminalDrop;
    @FindBy(xpath = "(//select[@id='chosenTerminal']//option[not(@disabled='disabled')])[1]")
    private WebElement firstTerminal;
    //endregion

    public LandingPage openHomePage(String homepageUrl) {
        driver.get(homepageUrl);
        return this;
    }
    public LandingPage acceptCookies() {
        waitHandlerClickable(acceptCookie);
        acceptCookie.click();
        return this;
    }
    public LandingPage fillEntryDates() {
        entryDateButton.click();
        selectEntryDate2Day.click();
        entryTimeButton.click();
        selectEntryTime.click();
        return this;
    }
    public LandingPage fillExitDates() {
        exitDateButton.click();
        selectExitDate5Day.click();
        //selectExitDateLast.click();
        exitTimeButton.click();
        selectExitTime.click();
        return this;
    }
    public LandingPage selectPassengers() {
        numberOfPassengers.click();
        return this;
    }
    public ProductPage submitForm() {
        waitHandlerClickable(submitForm);
        submitForm.click();
        return new ProductPage(driver);
    }

    public LandingPage selectTerminal() {
        terminalDrop.click();
        firstTerminal.click();
        return this;
    }

}
