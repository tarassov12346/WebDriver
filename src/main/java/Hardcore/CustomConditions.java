package Hardcore;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {
    public static ExpectedCondition<Boolean> pageLoadCompleted() {
        return driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
}
