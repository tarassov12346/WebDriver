package Hardcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class WebDriverHardcoreTest {
    WebDriver chromeDriver = StartScenarioOnBrowser(new ChromeDriver());
    TestResultPage testResult =
            new TestResultPage(chromeDriver, GoogleCloudPlatformPricingCalculatorPage.estimatedCostOnCalculator,
                    EmailHandlingPage.costValueFromEmail);

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new GoogleCloudPlatformPricingCalculatorPage(driver)
                .openPage()
                .searchForCalculatorSiteAndClick()
                .fillCalculatorSiteForm()
                .createEstimatedCostRequest()
                .openEmailGeneratorSiteAndTakeEmailName()
                .sendEmail()
                .getCostValueFromEmail();
        return driver;
    }

    @Test(description = "testHardcore Total Estimated Monthly Cost")
    public void totalEstimatedMonthlyCostCorresponds() {
        Assert.assertTrue(testResult.compareEstimatedCostOnCalculatorWithCostValueFromEmail(),
                chromeDriver.toString() + ": FAIL: the estimated cost on calculator differs from that in Email");
    }

    @AfterTest(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }

}
