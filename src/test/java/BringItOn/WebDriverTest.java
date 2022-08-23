package BringItOn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WebDriverTest {
    WebDriver driver;

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PageNavigator(driver)
                .openPage()
                .fillSiteForm()
                .createRequest();
        return driver;
    }

    @Test(description = "test2")
    public void scenarioTest() {
       driver = StartScenarioOnBrowser(new ChromeDriver());
        testProcedure(driver);
    }

    private void testProcedure(WebDriver driver) {
        PageTestResult testPage = new PageTestResult(driver);
        Assert.assertTrue(testPage.checkCodeText(), driver.toString() + ": FAIL:The text code does not correspond");
        Assert.assertTrue(testPage.checkSyntaxHighlight(), driver.toString() + ": FAIL:The highlight of syntax does not correspond");
        Assert.assertTrue(testPage.checkTitle(), driver.toString() + ": FAIL:The text of title does not correspond");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(driver);

    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }
}
