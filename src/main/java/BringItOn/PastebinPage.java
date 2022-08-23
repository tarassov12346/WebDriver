package BringItOn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PastebinPage extends AbstractPage{
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String CODE_FIELD_TEXT = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private static final String SYNTAX = "Bash";
    private static final String TITLE_TEXT = "how to gain dominance among developers";

    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement codeDescriptionField;
    @FindBy(xpath = "//*[@id='select2-postform-format-container']")
    private WebElement syntaxHighlightComboBox;
    @FindBy(xpath = "//*[@class='select2-results__option' and text()='" + SYNTAX + "']")
    private WebElement syntaxHighlightComboBoxSelect;
    @FindBy(xpath = "//*[@id='select2-postform-expiration-container']")
    private WebElement pasteExpirationField;
    @FindBy(xpath = "//*[@class='select2-results__option' and text()='" + EXPIRATION_TIME + "']")
    private WebElement pasteExpirationFieldSelect;
    @FindBy(xpath = "//*[@id='postform-name']")
    private WebElement titleField;
    @FindBy(xpath = "//*[@class='btn -big']")
    private WebElement createButton;

    public PastebinPage(WebDriver driver) {
        super(driver);
    }

    public static String getCodeFieldText() {
        return CODE_FIELD_TEXT;
    }

    public static String getSYNTAX() {
        return SYNTAX;
    }

    public static String getTitleText() {
        return TITLE_TEXT;
    }

    public PastebinPage openPage() {
        driver.manage().window().maximize();
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PastebinPage fillSiteForm() {
        codeDescriptionField.sendKeys(CODE_FIELD_TEXT);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(syntaxHighlightComboBox));
              syntaxHighlightComboBox.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(syntaxHighlightComboBoxSelect));
               syntaxHighlightComboBoxSelect.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pasteExpirationField));
               pasteExpirationField.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pasteExpirationFieldSelect));
        pasteExpirationFieldSelect.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(titleField));
        titleField.sendKeys(TITLE_TEXT);
        return this;
    }

    public PastebinPage createRequest() {
        createButton.click();
        System.out.println("URL: " + driver.getCurrentUrl());
        return this;
    }
}
