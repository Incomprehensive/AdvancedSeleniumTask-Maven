package com.pelian.learns.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;

public class SeleniumTest {

    @FindBy (xpath = "//div[@class = 'logged-in-as-wrap']/a[2]")
    protected WebElement logoutButton;

    @FindBy(xpath = "//input[@id = 'search-text']")
    protected WebElement searchInput;

    @FindBy (xpath = "//input[@id = 'search-submit']")
    protected WebElement searchSubmit;

    FileHandling fileHandling = new FileHandling();

    /**
     * Method to initialize WebDriver and necessary functions for a test
     */
    @Before
    public void initialize() {
        // Initializing WebDriver and WebElements PageFactory
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        fileHandling.driver = new ChromeDriver();
        PageFactory.initElements(fileHandling.driver, this);
        fileHandling.hold =  new WebDriverWait(fileHandling.driver, 5);
    }

    /**
     * Method to enter a website, log in using existing credentials, put a search inquiry and log out
     * @throws IOException
     */
    @Test
    public void testLoginSearch() throws IOException {
        // Login
        fileHandling.driver.get(fileHandling.website);
        fileHandling.logToFile("Enter a website");
        fileHandling.read();
        fileHandling.logToFile("Read the file with credentials");
        fileHandling.login();

        // Search
        fileHandling.logToFile("Enter login and password");
        searchInput.sendKeys("The infestation");
        searchSubmit.click();
        fileHandling.logToFile("Enter a query in a search field");

        // Log out
        fileHandling.hold.until(ExpectedConditions.elementToBeClickable(logoutButton));
        fileHandling.logToFile("Wait for logout button to be clickable");
        logoutButton.click();
        fileHandling.logToFile("Logout");
    }

    @After
    public void close() {
        // Close all instances of WebDriver
        fileHandling.driver.quit();
    }
}