package ICanWin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageNavigator extends AbstractPage{
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String CODE_FIELD_TEXT = "Hello from WebDriver";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private static final String TITLE_TEXT = "helloweb";

    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement codeDescriptionField;
    @FindBy(xpath = "//*[@id='select2-postform-expiration-container']")
    private WebElement pasteExpirationField;
    @FindBy(xpath = "//*[@class='select2-results__option' and text()='" + EXPIRATION_TIME + "']")
    private WebElement pasteExpirationFieldSelect;
    @FindBy(xpath = "//*[@id='postform-name']")
    private WebElement titleField;
    @FindBy(xpath = "//*[@class='btn -big']")
    private WebElement createButton;

    public PageNavigator(WebDriver driver) {
        super(driver);
    }

    public PageNavigator openPage() {
        driver.manage().window().maximize();
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PageNavigator fillSiteForm() {
        codeDescriptionField.sendKeys(CODE_FIELD_TEXT);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pasteExpirationField));
        pasteExpirationField.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pasteExpirationFieldSelect));
        pasteExpirationFieldSelect.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(titleField));
        titleField.sendKeys(TITLE_TEXT);
        return this;
    }

    public PageNavigator createPaste() {
        createButton.click();
        System.out.println("URL: " + driver.getCurrentUrl());
        return this;
    }
}
