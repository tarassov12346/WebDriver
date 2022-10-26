package Hardcore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage() {
       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();


        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected abstract AbstractPage openPage(String url);
}
