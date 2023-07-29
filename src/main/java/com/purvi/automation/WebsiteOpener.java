package com.purvi.automation;

import com.purvi.automation.model.CredentialData;
import com.purvi.automation.model.LocatorData;
import com.purvi.automation.model.WebsiteData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class WebsiteOpener {

    public void open(Map<String, CredentialData> credentialData,
                     Map<String, LocatorData> locatorData,
                     Map<String, WebsiteData> websiteData) {
        // Set the path to the ChromeDriver executable (You need to download ChromeDriver separately)
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\ChromeDriver\\ChromeDriver.exe");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to a URL
        for(String id : credentialData.keySet()) {
            driver.get(websiteData.get(id).website());
            driver.manage().window().maximize();
            Duration timeout = Duration.of(10, ChronoUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            String frameID = "logonbox";

            driver.switchTo().frame(frameID);
            driver.findElement(By.id(locatorData.get(id).usernameXpathId())).sendKeys(credentialData.get(id).username());
            driver.findElement(By.id(locatorData.get(id).passwordXpathId())).sendKeys(credentialData.get(id).password());
            driver.findElement(By.id(locatorData.get(id).signInButtonId())).click();
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.urlContains("recognizeUser/simplerAuthOptions"));

            if (driver.getCurrentUrl().contains("recognizeUser/simplerAuthOptions")) {
                    driver.findElement(By.id(locatorData.get(id).notificationButtonId())).click();
                    driver.findElement(By.id(locatorData.get(id).requestCodeId())).click();
            }
        }
    }

}

