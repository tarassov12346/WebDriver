package ICanWin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class WebDriverICanWinTest {
    WebDriver chromeDriver= StartScenarioOnBrowser(new ChromeDriver());;

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PastebinPage(driver)
                .openPage()
                .fillSiteForm()
                .createPaste();
        return driver;
    }

     @Test(description = "testICanWin")
    public void scenarioTest() {
         System.out.println(chromeDriver);
    }

    @AfterTest(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }
}
