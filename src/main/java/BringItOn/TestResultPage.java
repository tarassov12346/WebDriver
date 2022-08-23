package BringItOn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestResultPage extends AbstractPage{
    private Boolean result;

    @FindBy(xpath = "//*[@class='textarea -raw js-paste-raw']")
    private WebElement codeText;
    @FindBy(xpath = "//a[@href='/archive/bash']")
    private WebElement syntaxHighlight;
    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement titleText;

    public TestResultPage(WebDriver driver) {
        super(driver);
    }

    public Boolean checkCodeText() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(codeText));
        checkResult(codeText.getAttribute("value"), PastebinPage.getCodeFieldText());
        return result;
    }

    public Boolean checkSyntaxHighlight() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(syntaxHighlight));
        checkResult(syntaxHighlight.getText(), PastebinPage.getSYNTAX());
        return result;
    }

    public Boolean checkTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(titleText));
        checkResult(titleText.getText(), PastebinPage.getTitleText());
        return result;
    }

    private void checkResult(String textOnPage, String textToCompare) {
        result = textOnPage.equals(textToCompare);
        if (result)
            System.out.println(driver.toString() + ": test passed, the text '" + textToCompare + "' is present");
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
