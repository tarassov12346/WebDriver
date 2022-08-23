package BringItOn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class WebDriverBringItOnTest {
    WebDriver chromeDriver = StartScenarioOnBrowser(new ChromeDriver());
    TestResultPage testPage = new TestResultPage(chromeDriver);

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PastebinPage(driver)
                .openPage()
                .fillSiteForm()
                .createRequest();
        return driver;
    }

    @Test(description = "testBringItOn PasteName/Title Check")
    public void titleCorresponds() {
        Assert.assertTrue(testPage.checkTitle(), chromeDriver.toString() + ": FAIL:The text of title does not correspond");
    }

    @Test(description = "testBringItOn SyntaxHighlight Check")
    public void syntaxHighlightCorresponds() {
        Assert.assertTrue(testPage.checkSyntaxHighlight(), chromeDriver.toString() + ": FAIL:The highlight of syntax does not correspond");
    }

    @Test(description = "testBringItOn CodeText Check")
    public void codeTextCorresponds() {
        Assert.assertTrue(testPage.checkCodeText(), chromeDriver.toString() + ": FAIL:The text code does not correspond");
    }

    @AfterTest(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }
}
