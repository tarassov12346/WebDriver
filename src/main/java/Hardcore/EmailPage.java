package Hardcore;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EmailPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='listeliens']//*[@class='txtlien']")
    private WebElement generateEmailButton;
    @FindBy(id = "egen")
    private WebElement generatedEmailNameField;
    @FindBy(xpath = "//h2[contains(text(),'Estimated Monthly Cost: USD')]")
    private WebElement costValueFromEmailField;
    @FindBy(xpath = "//span[text()='Проверить почту']")
    private WebElement checkEmailButton;
    @FindBy(id = "refresh")
    private WebElement refreshEmailWindowButton;
    @FindBy(id = "nbmail")
    private WebElement mail;

    @Override
    protected EmailPage openPage(String urlNameOfEmailGenerator) {
        driver.get(urlNameOfEmailGenerator);
        return this;
    }

    public EmailPage clickGenerateEmailButton(){
        generateEmailButton.click();
        return this;
    }

    public String getEmailName() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(CustomConditions.pageLoadCompleted());
        String generatedEmailName = generatedEmailNameField.getText();
        System.out.println("Generated email: " + generatedEmailName);
        return generatedEmailName;
    }

    public EmailPage clickCheckEmailButton() {
        checkEmailButton.click();
        return this;
    }

    public EmailPage clickRefreshButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(refreshEmailWindowButton));
        while (mail.getText().equals("0 mail")) {
            refreshEmailWindowButton.click();
        }
        return this;
    }

    public String getCostInEmail() {
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='ifmail']")));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(costValueFromEmailField));
        return getCostFromText(costValueFromEmailField.getText());
    }

    private String getCostFromText(String text) {
        String[] fulltext = text.split(" ");
        return fulltext[4];
    }
}
