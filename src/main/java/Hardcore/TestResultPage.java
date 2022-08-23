package Hardcore;

import org.openqa.selenium.WebDriver;

public class TestResultPage extends AbstractPage {
    private String estimatedCostOnCalculator;
    private String costValueFromEmail;

    public TestResultPage(WebDriver driver, String estimatedCostOnCalculator, String costValueFromEmail) {
        super(driver);
        this.estimatedCostOnCalculator = estimatedCostOnCalculator;
        this.costValueFromEmail = costValueFromEmail;
    }

    public boolean compareEstimatedCostOnCalculatorWithCostValueFromEmail() {
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
