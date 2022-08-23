package HurtMePlenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        PageNavigator pageNavigator=new PageNavigator(driver);
        pageNavigator.openPage();
        pageNavigator.searchForCalculatorSiteAndClick();
        pageNavigator.fillCalculatorSiteForm();
        pageNavigator.createEstimatedCostRequest();
  //      List<WebElement> listOfWebElement = driver.findElements(By.xpath("//*[@id='compute']/md-list[1]/md-list-item[8]/div[1]"));
  //      for (WebElement list : listOfWebElement) {
 //           System.out.println(list.getText());}

    }
}
