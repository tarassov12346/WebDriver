package HurtMePlenty;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class GoogleCloudPlatformPricingCalculatorPage extends AbstractPage {
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

    @FindBy(xpath = "//*[@class='devsite-search-field devsite-search-query']")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@class='gsc-results gsc-webResult']")
    private WebElement searchResultField;
    @FindBy(xpath = "//md-input-container//label[contains(text(),'Number of instances')]/../input")
    //@FindBy(xpath = "//*[@id='input_90']")
    private WebElement inputFieldNumberOfInstances;
    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']//*[@class='md-container md-ink-ripple']")
    private WebElement checkBoxAddGPUs;
    @FindBy(xpath = "//*[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement buttonAddToEstimate;


    public GoogleCloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public static String getFormClassType() {
        return FORM_CLASS_TYPE;
    }

    public static String getFormInstanceType() {
        return FORM_INSTANCE_TYPE;
    }

    public static String getFormSsdCapacity() {
        return FORM_SSD_CAPACITY;
    }

    public static String getFormLocation() {
        return FORM_LOCATION;
    }

    public static String getFormUsage() {
        return FORM_USAGE;
    }

    public GoogleCloudPlatformPricingCalculatorPage openPage() {
        driver.manage().window().maximize();
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage searchForCalculatorSiteAndClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        searchButton.sendKeys(SEARCH_REQUEST);
        searchButton.sendKeys(Keys.RETURN);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(searchResultField));
        searchResultField.findElement(By.linkText(SEARCH_REQUEST)).click();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage fillCalculatorSiteForm() {
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']/devsite-iframe/iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));
        inputFieldHandling(inputFieldNumberOfInstances, FORM_NUMBER_OF_INSTANCE);
        spanOptionHandling("Operating System / Software", FORM_OS_TYPE);
        spanOptionHandling("Provisioning model", FORM_CLASS_TYPE);
        spanOptionHandling("Series", FORM_INSTANCE_SERIES);
        spanOptionHandling("Machine type", FORM_INSTANCE_TYPE);
        checkBoxHandling(checkBoxAddGPUs);
        spanOptionHandling("GPU type", FORM_GPU_TYPE);
        spanOptionHandling("Number of GPUs", FORM_GPU_NUMBER);
        spanOptionHandling("Local SSD", FORM_SSD_CAPACITY);
        datacenterLocationSpanOptionHandling("Datacenter location", FORM_LOCATION);
        spanOptionHandling("Committed usage", FORM_USAGE);
        return this;
    }

    private void inputFieldHandling(WebElement inputField, String value) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(inputField));
        inputField.sendKeys(value);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
    }

    public void spanOptionHandling(String optionBoxName, String optionName) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionBoxName + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionBoxName + "']/../md-select")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + optionName + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + optionName + "')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
    }

    private void checkBoxHandling(WebElement checkBoxField) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(checkBoxField));
        checkBoxField.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
    }

    public void datacenterLocationSpanOptionHandling(String optionBoxName, String optionName) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionBoxName + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionBoxName + "']/../md-select")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + optionName + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + optionName + "')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
    }

    public void createEstimatedCostRequest() {
        buttonAddToEstimate.click();
    }
}
