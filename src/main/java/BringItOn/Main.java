package BringItOn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        PageNavigator pageNavigator=new PageNavigator(driver);
        pageNavigator.openPage();
        pageNavigator.fillSiteForm();
        pageNavigator.createRequest();
    }
}
