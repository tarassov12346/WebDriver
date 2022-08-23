package ICanWin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WebDriverTest {
    WebDriver chromeDriver;

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PageNavigator(driver)
                .openPage()
                .fillSiteForm()
                .createPaste();
        return driver;
    }

     @Test(description = "test")
    public void scenarioTest() {
        chromeDriver = StartScenarioOnBrowser(new ChromeDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }
}
