package com.pelian.learns.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.*;

public class SeleniumTest {
    protected static WebDriver driver;

    protected String website = "https://rutracker.org";

    FileHandling fileHandling = new FileHandling();

    SignUp signUp = new SignUp(driver);

    SearchPage searchPage = new SearchPage(driver);
    /**
     * Method to initialize WebDriver and necessary functions for a test
     */
    @Before
    public void initialize() {
        // Initializing WebDriver and WebElements PageFactory
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to enter a website, log in using existing credentials, put a search inquiry and log out
     * @throws IOException
     */
    @Test
    public void testLoginSearch() throws IOException {

        // Login
        driver.get(website);
        fileHandling.logToFile("Enter a website");
        fileHandling.read();
        fileHandling.logToFile("Read the file with credentials");
        signUp.login(fileHandling.user, fileHandling.password);

        // Search
        fileHandling.logToFile("Enter login and password");
        fileHandling.logToFile("Enter a query in a search field");

        // Scan the results and write them to the file
        searchPage.CommitSearch();
        searchPage.resultsToFile(fileHandling.results);

        // Log out
        signUp.hold.until(ExpectedConditions.elementToBeClickable(signUp.logoutButton));
        fileHandling.logToFile("Wait for logout button to be clickable");
        signUp.logoutButton.click();
        fileHandling.logToFile("Logout");
    }

    @After
    public void close() {
        // Close all instances of WebDriver
        driver.quit();
    }
}