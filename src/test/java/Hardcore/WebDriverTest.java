package Hardcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class WebDriverTest {
    WebDriver chromeDriver= StartScenarioOnBrowser(new ChromeDriver());

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PageNavigator(driver)
                .openPage()
                .searchForCalculatorSiteAndClick()
                .fillCalculatorSiteForm()
                .createEstimatedCostRequest()
                .openEmailGeneratorSiteAndTakeEmailName()
                .sendEmail()
                .verifyCostInEmail();
        return driver;
    }

    @Test(description = "testHardcore Total Estimated Monthly Cost")
    public void totalEstimatedMonthlyCostCorresponds() {
        Assert.assertTrue(PageTestResult.result, chromeDriver.toString() + ": FAIL: the estimated cost on calculator differs from that in Email");
    }

    @AfterTest(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }

}
