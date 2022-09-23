package Hardcore;

public class VirtualMachineDesignShop {
    GoogleCloudPlatformPricingCalculatorPage googleCloudPlatformPricingCalculatorPage =
            new GoogleCloudPlatformPricingCalculatorPage();

    public String getVmEstimatedMonthlyCostValueOnCalculator() {
        return googleCloudPlatformPricingCalculatorPage.
                getVmEstimatedMonthlyCostValueOnCalculator();
    }

    public VirtualMachineDesignShop getCalculator(String homePageUrl, String searchRequest) {
        googleCloudPlatformPricingCalculatorPage.openPage(homePageUrl).clickSearchButton().
                enterSearchRequest(searchRequest).loadCalculatorSite(searchRequest);
        return this;
    }

    public VirtualMachineDesignShop
    calculateTheVM(String formNumberOfInstance, String formOsType, String formClassType,
                   String formInstanceSeries, String formInstanceType, String formGpuNumber,
                   String formGpuType, String formSsdCapacity, String formLocation, String formUsage) {
        googleCloudPlatformPricingCalculatorPage.fillInNumberOfInstance(formNumberOfInstance).
                fillInOperatingSystem(formOsType).fillInVmClass(formClassType).fillInSeries(formInstanceSeries).
                fillInMachineType(formInstanceType).checkBoxAddGpu().fillInGpuType(formGpuType).
                fillInNumberOfGpu(formGpuNumber).fillInLocalSsd(formSsdCapacity).
                fillInDataCenterLocation(formLocation).fillInCommitedUsage(formUsage).
                addToEstimate();
        return this;
    }

    public EmailHandling sendCalculatedCostToEmail(String emailGeneratorUrl) {
        EmailHandling emailHandling = new EmailHandling();
        googleCloudPlatformPricingCalculatorPage.clickEmailButton().
                inputGeneratedEmailName(emailHandling.getGeneratedEmailName(emailGeneratorUrl));
        return emailHandling;
    }
}
