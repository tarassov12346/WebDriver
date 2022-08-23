package BringItOn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class WebDriverTest {
    WebDriver driver = StartScenarioOnBrowser(new ChromeDriver());
    PageTestResult testPage = new PageTestResult(driver);

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PageNavigator(driver)
                .openPage()
                .fillSiteForm()
                .createRequest();
        return driver;
    }

    @Test(description = "testBringItOn PasteName/Title Check")
    public void titleCorresponds() {
        Assert.assertTrue(testPage.checkTitle(), driver.toString() + ": FAIL:The text of title does not correspond");
    }

    @Test(description = "testBringItOn SyntaxHighlight Check")
    public void syntaxHighlightCorresponds() {
        Assert.assertTrue(testPage.checkSyntaxHighlight(), driver.toString() + ": FAIL:The highlight of syntax does not correspond");
    }

    @Test(description = "testBringItOn CodeText Check")
    public void codeTextCorresponds() {
        Assert.assertTrue(testPage.checkCodeText(), driver.toString() + ": FAIL:The text code does not correspond");
    }

    @AfterTest(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(driver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }
}
