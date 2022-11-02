package HurtMePlenty;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

//new FirefoxDriver()
//new ChromeDriver()
//new EdgeDriver()

public class WebDriverHurtMePlentyTest {

    WebDriver chromeDriver= StartScenarioOnBrowser(new ChromeDriver());


    TestResultPage testPage = new TestResultPage(chromeDriver);

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new GoogleCloudPlatformPricingCalculatorPage(driver)
                .openPage()
                .searchForCalculatorSiteAndClick()
                .fillCalculatorSiteForm()
                .createEstimatedCostRequest();
        return driver;
    }

    @Test(description = "testHurtMePlenty VM Class Check")
    public void vMClassCorresponds() {
        Assert.assertTrue(testPage.checkVMClass(), chromeDriver.toString() + ": FAIL: for VM Class check");
    }

    @Test(description = "testHurtMePlenty Instance Type Check")
    public void instanceTypeCorresponds() {
        Assert.assertTrue(testPage.checkInstanceType(), chromeDriver.toString() + ": FAIL: for Instance Type check");
    }

    @Test(description = "testHurtMePlenty Region Check")
    public void regionCorresponds() {
        Assert.assertTrue(testPage.checkRegion(), chromeDriver.toString() + ": FAIL: for Region check");
    }

    @Test(description = "testHurtMePlenty local SSD Check")
    public void localSsdCorresponds() {
        Assert.assertTrue(testPage.checkSSD(), chromeDriver.toString() + ": FAIL: for SSD capacity check");
    }

    @Test(description = "testHurtMePlenty commitment term Check")
    public void commitmentTermCorresponds() {
        Assert.assertTrue(testPage.checkUsage(), chromeDriver.toString() + ": FAIL: for Usage term check");
    }

    @Test(description = "testHurtMePlenty manual result Check")
    public void manualResultCorresponds() {
        Assert.assertTrue(testPage.checkManualResult(), chromeDriver.toString() + ": FAIL: for manual result check");
    }

    @AfterTest(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }
}
