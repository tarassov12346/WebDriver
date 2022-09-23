package Hardcore;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriverHardcoreTest {
    String vmTotalEstimatedMonthlyCostValueOnCalculator;
    String vmTotalEstimatedMonthlyCostValueInEmail;
    VirtualMachineDesignShop vmDesigner;
    EmailHandling emailHandler;
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_REQUEST = "Google Cloud Platform Pricing Calculator";
    private static final String FORM_NUMBER_OF_INSTANCE = "4";
    private static final String FORM_OS_TYPE = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    private static final String FORM_CLASS_TYPE = "Regular";
    private static final String FORM_INSTANCE_SERIES = "N1";
    private static final String FORM_INSTANCE_TYPE = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private static final String FORM_GPU_NUMBER = "1";
    private static final String FORM_GPU_TYPE = "NVIDIA Tesla V100";
    private static final String FORM_SSD_CAPACITY = "2x375 GB";
    private static final String FORM_LOCATION = "Frankfurt (europe-west3)";
    private static final String FORM_USAGE = "1 Year";
    private static final String EMAIL_GENERATOR_URL = "https://yopmail.com/";

    @BeforeClass(description = "calculates,sends result and obtains VM Total Estimated Monthly Cost values")
    private void calculateSendObtainVmEstimatedCostValues() {
        vmDesigner = new VirtualMachineDesignShop();
        emailHandler = vmDesigner.getCalculator(HOMEPAGE_URL,SEARCH_REQUEST).
                calculateTheVM(FORM_NUMBER_OF_INSTANCE, FORM_OS_TYPE, FORM_CLASS_TYPE,
                        FORM_INSTANCE_SERIES, FORM_INSTANCE_TYPE, FORM_GPU_NUMBER, FORM_GPU_TYPE,
                        FORM_SSD_CAPACITY, FORM_LOCATION, FORM_USAGE).
                sendCalculatedCostToEmail(EMAIL_GENERATOR_URL).checkEmail();
        vmTotalEstimatedMonthlyCostValueOnCalculator = vmDesigner.getVmEstimatedMonthlyCostValueOnCalculator();
        vmTotalEstimatedMonthlyCostValueInEmail = emailHandler.getVmEstimatedMonthlyCostValueInEmail();
        System.out.printf("vmTotalEstimatedMonthlyCostValueOnCalculator %s",vmTotalEstimatedMonthlyCostValueOnCalculator);
        System.out.printf("\n vmTotalEstimatedMonthlyCostValueInEmail %s",vmTotalEstimatedMonthlyCostValueInEmail);
    }

    @Test(description = "compares VM Total Estimated Monthly Cost values on Calculator and in Email")
    public void areTotalEstimatedMonthlyCostValuesOnCalculatorAndInEmailEqual() {
        Assert.assertEquals(vmTotalEstimatedMonthlyCostValueOnCalculator, vmTotalEstimatedMonthlyCostValueInEmail,
                ": FAIL: the estimated cost on calculator " + vmTotalEstimatedMonthlyCostValueOnCalculator + " differs from that in Email " + vmTotalEstimatedMonthlyCostValueInEmail);
    }

    @AfterClass(description = "closes the browser instances")
    public void afterTestCompleted() {
        vmDesigner.googleCloudPlatformPricingCalculatorPage.getDriver().quit();
        emailHandler.emailPage.getDriver().quit();
    }
}
