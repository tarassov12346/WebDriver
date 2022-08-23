package Hardcore;

import org.openqa.selenium.WebDriver;

public class PageTestResult extends AbstractPage{
    public static Boolean result;
    private String estimatedCostOnCalculator;
    private String costValueFromEmail;

    public PageTestResult(WebDriver driver, String estimatedCostOnCalculator, String costValueFromEmail) {
        super(driver);
        this.estimatedCostOnCalculator = estimatedCostOnCalculator;
        this.costValueFromEmail = costValueFromEmail;
        result = checkEmail();
        System.out.println("final result: " + result);
    }

    public boolean checkEmail() {
        System.out.println("Start to check the results!");
        System.out.println("estimatedCostOnCalculator: " + estimatedCostOnCalculator);
        System.out.println("costValueFromEmail: " + costValueFromEmail);
        System.out.println("result of comparison: " + costValueFromEmail.contains(estimatedCostOnCalculator));
        return costValueFromEmail.contains(estimatedCostOnCalculator);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
