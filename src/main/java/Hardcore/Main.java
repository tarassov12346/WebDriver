package Hardcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        PageNavigator pageNavigator=new PageNavigator(driver);
        pageNavigator.openPage();
        pageNavigator.searchForCalculatorSiteAndClick();
        pageNavigator.fillCalculatorSiteForm();
        pageNavigator.createEstimatedCostRequest().openEmailGeneratorSiteAndTakeEmailName().sendEmail().verifyCostInEmail();
    }
}
