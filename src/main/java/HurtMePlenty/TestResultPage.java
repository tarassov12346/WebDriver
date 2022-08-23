package HurtMePlenty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TestResultPage extends AbstractPage{
    private Boolean result;

    public TestResultPage(WebDriver driver) {
        super(driver);
    }

    public Boolean checkVMClass() {
        String classType = GoogleCloudPlatformPricingCalculatorPage.getFormClassType();
        checkResult(classType);
        return result;
    }

    public Boolean checkInstanceType() {
        String instanceType= GoogleCloudPlatformPricingCalculatorPage.getFormInstanceType();
        checkResult(instanceType.substring(0,instanceType.indexOf(' ')));
        return result;
    }

    public Boolean checkRegion() {
        String region= GoogleCloudPlatformPricingCalculatorPage.getFormLocation();
        checkResult(region.substring(0,region.indexOf(' ')));
        return result;
    }

    public Boolean checkSSD() {
        String ssd= GoogleCloudPlatformPricingCalculatorPage.getFormSsdCapacity();
        checkResult(ssd.substring(0,ssd.indexOf(' ')));
        return result;
    }

    public Boolean checkUsage() {
        checkResult(GoogleCloudPlatformPricingCalculatorPage.getFormUsage());
        return result;
    }

    private void checkResult(String textToCompare) {
        List<WebElement> listOfWebElement = driver.findElements(By.xpath("//*[@class='md-list-item-text ng-binding']"));
     //   WebElement elementInstanceType = driver.findElement(By.xpath("//*[@id='compute']/md-list[1]/md-list-item[5]/div[1]"));
        WebElement elementInstanceType = driver.findElement(By.xpath("//div[contains(text(),'Instance type:')]"));
    //    WebElement elementLocalSSD = driver.findElement(By.xpath("//*[@id='compute']/md-list[1]/md-list-item[7]/div[1]"));
        WebElement elementLocalSSD = driver.findElement(By.xpath("//div[contains(text(),'Local SSD:')]"));
        listOfWebElement.add(elementInstanceType);
        listOfWebElement.add(elementLocalSSD);
        for (WebElement list : listOfWebElement) {
            if (list.getText().contains(textToCompare)) {
                System.out.println("PASSED: [" + list.getText() + "] contains: " + textToCompare);
                result = true;
                return;
            }
        }
        result = false;
    }

    public Boolean checkManualResult(){
        WebElement elementCost = driver.findElement(By.xpath("//b[contains(text(),'Total Estimated Cost:')]"));
        //WebElement elementCost = driver.findElement(By.xpath("//*[@id='compute']/md-list[1]/md-list-item[8]/div[1]"));
        String[] elementSum=elementCost.getText().split(" ");
        if(elementSum[4].equals("1,081.20")) {
            System.out.println("PASSED: "+elementCost.getText()+" equals to 1,081.20 USD");
            result=true;
        }
        return result;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
