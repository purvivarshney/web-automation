package com.purvi.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class BankAccountOpening {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable (You need to download ChromeDriver separately)
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\ChromeDriver\\ChromeDriver.exe");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to a URL
        driver.get("https://www.chase.com");
        driver.manage().window().maximize();
        String frameID= "logonbox";

        driver.switchTo().frame(frameID);
        Duration timeout = Duration.of(10, ChronoUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement userIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userId-text-input-field")));
        userIdField.sendKeys("");
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password-text-input-field")));
        passwordField.sendKeys("");
        driver.findElement(By.id("signin-button")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//*[@id=\"label-sec-auth-options-0\"]")).click();

        // WebDriverWait wait = new WebDriverWait(driver, 90);
        driver.findElement(By.xpath("//*[@id=\"requestIdentificationCode\"]")).click();
    }
}

