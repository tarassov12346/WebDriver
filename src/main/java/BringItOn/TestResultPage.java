package BringItOn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.StringJoiner;

public class TestResultPage extends AbstractPage{
    private Boolean result;

    //@FindBy(xpath = "//*[@class='textarea -raw js-paste-raw']")
    //private WebElement codeTextInResultForm;

    @FindBy(xpath = "//a[@href='/archive/bash']")
    private WebElement syntaxHighlightInResultForm;
    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement titleTextInResultForm;

    public TestResultPage(WebDriver driver) {
        super(driver);
    }

    public Boolean checkCodeText() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='de1']")));
        List<WebElement> codeTextInResultFormList= driver.findElements(By.xpath("//div[@class='de1']"));
        StringJoiner codeTextInResultFormJoiner = new StringJoiner("\n");
        codeTextInResultFormList.stream().forEach(webElement -> codeTextInResultFormJoiner.add(webElement.getText()));
        checkResult(codeTextInResultFormJoiner.toString(), PastebinPage.getCodeFieldText());
        return result;
    }

    public Boolean checkSyntaxHighlight() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(syntaxHighlightInResultForm));
        checkResult(syntaxHighlightInResultForm.getText(), PastebinPage.getSYNTAX());
        return result;
    }

    public Boolean checkTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(titleTextInResultForm));
        checkResult(titleTextInResultForm.getText(), PastebinPage.getTitleText());
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
