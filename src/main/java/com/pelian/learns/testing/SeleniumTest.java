package com.pelian.learns.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;

public class SeleniumTest extends FileHandling{
    @FindBy(xpath = "//input[@name = 'login_username']")
    private WebElement username;

    @FindBy (xpath = "//input[@name = 'login_password']")
    private WebElement pass;

    @FindBy (xpath = "//div[@class = 'logged-in-as-wrap']/a[2]")
    private WebElement logoutButton;

    @FindBy (xpath = "//span[@class = 'a-like bold']")
    private WebElement loginButtonForm;

    @FindBy (xpath = "//input[@name = 'login']")
    private WebElement loginButtonSubmit;

    @FindBy (xpath = "//div[@class = 'logged-in-as-wrap']/a[1]")
    private WebElement loggedUser;

    @FindBy (xpath = "//input[@id = 'search-text']")
    private WebElement searchInput;

    @FindBy (xpath = "//input[@id = 'search-submit']")
    private WebElement searchSubmit;
    private WebDriver driver;
    private WebDriverWait hold;

    @Before
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        hold =  new WebDriverWait(driver, 5);
    }

    public void login() throws IOException {
        hold.until(ExpectedConditions.elementToBeClickable(loginButtonForm));
        loginButtonForm.click();
        username.sendKeys(user);
        pass.sendKeys(password);
        loginButtonSubmit.click();
    }

    @Test
    public void testLoginSearch() throws IOException {
        driver.get("https://rutracker.org");
        logToFile("Enter a website");
        read();
        logToFile("Read the file with credentials");
        login();
        logToFile("Enter login and password");
        searchInput.sendKeys("The infestation");
        searchSubmit.click();
        logToFile("Enter a query in a search field");
        hold.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logToFile("Wait for logout button to be clickable");
        logoutButton.click();
        logToFile("Logout");
    }

    @After
    public void close() {
        driver.close();
    }
}
