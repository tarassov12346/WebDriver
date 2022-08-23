package Hardcore;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;


public class EmailPageNavigator extends AbstractPage {
    String costValueFromEmail;
    String generatedEmailName;
    String estimatedCostOnCalculator;
    String urlNameOfEmailGenerator;

    // @FindBy(xpath = "//*[@id='listeliens']/a[1]")
    @FindBy(xpath = "//*[@id='listeliens']//*[@class='txtlien']")
    private WebElement generateEmailButton;
    @FindBy(xpath = "//*[@id='egen']")
    private WebElement generatedEmailNameField;
    //@FindBy(xpath = "//*[@id='mail']/div/div/table/tbody/tr[2]/td/h2")
    @FindBy(xpath = "//h2[contains(text(),'Estimated Monthly Cost: USD')]")
    private WebElement costValueFromEmailField;

    protected EmailPageNavigator(WebDriver driver, String urlNameOfEmailGenerator) {
        super(driver);
        this.urlNameOfEmailGenerator = urlNameOfEmailGenerator;
    }

    protected EmailPageNavigator(WebDriver driver, String estimatedCostOnCalculator, String generatedEmailName) {
        super(driver);
        this.generatedEmailName = generatedEmailName;
        this.estimatedCostOnCalculator = estimatedCostOnCalculator;
        costValueFromEmail = getCostValueFromEmail();
    }

    public PageNavigator openEmailGeneratorSiteAndTakeEmailName() {
        ((JavascriptExecutor) driver).executeScript("window.open();");
        ArrayList<String> windowsTabsList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsTabsList.get(1));
        driver.get(urlNameOfEmailGenerator);
        generateEmailButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(CustomConditions.pageLoadCompleted());
        generatedEmailName = generatedEmailNameField.getText();
        System.out.println("Generated email: " + generatedEmailName);
        driver.switchTo().window(windowsTabsList.get(0));
        return new PageNavigator(driver, generatedEmailName, windowsTabsList);
    }

    public String getCostValueFromEmail() {
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='ifmail']")));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(costValueFromEmailField));
        costValueFromEmail = costValueFromEmailField.getText();
        System.out.println("Cost value from Email: " + costValueFromEmail);
        return costValueFromEmail;
    }







    public PageTestResult verifyCostInEmail() {
        return new PageTestResult(driver, estimatedCostOnCalculator, costValueFromEmail);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}