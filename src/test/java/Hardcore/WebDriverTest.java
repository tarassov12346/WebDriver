package Hardcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebDriverTest {
    WebDriver chromeDriver;

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

    @Test(description = "test")
    public void scenarioTest() {
        chromeDriver = StartScenarioOnBrowser(new ChromeDriver());
        testProcedure(chromeDriver);
    }

    private void testProcedure(WebDriver driver) {
        System.out.println("result(assert method): " + PageTestResult.result);
        Assert.assertTrue(PageTestResult.result, driver.toString() + ": FAIL: the estimated cost on calculator differs from that in Email");
    }
}
