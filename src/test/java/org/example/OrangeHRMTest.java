package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrangeHRMTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @Test
    void validLoginTest() {

        // Enter username
        wait.until(d -> d.findElement(
                By.name("username")
        )).sendKeys("Admin");

        // Enter password
        wait.until(d -> d.findElement(
                By.name("password")
        )).sendKeys("admin123");

        // Click Login
        wait.until(d -> d.findElement(
                By.cssSelector("button[type='submit']")
        )).click();

        // Wait until the dashboard URL appears
        wait.until(d ->
                d.getCurrentUrl().contains("/dashboard")
        );

        // Verify successful login
        assertTrue(
                driver.getCurrentUrl().contains("/dashboard"),
                "Valid login should open the dashboard"
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}