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
        List<WebElement> listOfWebElementsInTheResultForm = driver.findElements(By.xpath("//*[@class='md-list-item-text ng-binding']"));
        WebElement elementInstanceTypeInResultForm = driver.findElement(By.xpath("//div[contains(text(),'Instance type:')]"));
        WebElement elementLocalSSDInResultForm = driver.findElement(By.xpath("//div[contains(text(),'Local SSD:')]"));
        listOfWebElementsInTheResultForm.add(elementInstanceTypeInResultForm);
        listOfWebElementsInTheResultForm.add(elementLocalSSDInResultForm);
        for (WebElement element : listOfWebElementsInTheResultForm) {
            if (element.getText().contains(textToCompare)) {
                System.out.println("PASSED: [" + element.getText() + "] contains: " + textToCompare);
                result = true;
                return;
            }
        }
        result = false;
    }

    public Boolean checkManualResult(){
        WebElement elementCostInResultForm = driver.findElement(By.xpath("//b[contains(text(),'Total Estimated Cost:')]"));
        String[] elementSum=elementCostInResultForm.getText().split(" ");
        if(elementSum[4].equals("1,081.20")) {
            System.out.println("PASSED: "+elementCostInResultForm.getText()+" equals to 1,081.20 USD");
            result=true;
        }
        return result;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
