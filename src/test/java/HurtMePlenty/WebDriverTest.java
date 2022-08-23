package HurtMePlenty;

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
                .createEstimatedCostRequest();
        return driver;
    }

    @Test(description = "test")
    public void scenarioTest() {
        chromeDriver = StartScenarioOnBrowser(new ChromeDriver());
        testProcedure(chromeDriver);
    }

    private void testProcedure(WebDriver driver) {
        PageTestResult testPage = new PageTestResult(driver);
        Assert.assertTrue(testPage.checkVMClass(), driver.toString() + ": FAIL: for VM Class check");
        Assert.assertTrue(testPage.checkInstanceType(), driver.toString() + ": FAIL: for Instance Type check");
        Assert.assertTrue(testPage.checkRegion(), driver.toString() + ": FAIL: for Region check");
        Assert.assertTrue(testPage.checkSSD(), driver.toString() + ": FAIL: for SSD capacity check");
        Assert.assertTrue(testPage.checkUsage(), driver.toString() + ": FAIL: for Usage term check");
        Assert.assertTrue(testPage.checkManualResult(), driver.toString() + ": FAIL: for manual result check");
    }
}
