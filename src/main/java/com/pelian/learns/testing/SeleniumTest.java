package com.pelian.learns.testing;

import org.junit.After;
import org.junit.Assert;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created by Anton Pelianski on 30.06.2017.
 */
public class SeleniumTest {
    @FindBy(id = "top-login-uname")
    private WebElement username;

    @FindBy (id = "top-login-pwd")
    private WebElement pass;

    @FindBy (xpath = "//div[@class = 'logged-in-as-wrap']/a[2]")
    private WebElement logoutButton;

    @FindBy (xpath = "//span[@class = 'a-like bold']")
    private WebElement loginButtonForm;

    @FindBy (id="top-login-btn")
    private WebElement loginButtonConfirm;

    @FindBy (xpath = "//div[@class = 'logged-in-as-wrap']/a[1]")
    private WebElement verify;

    @FindBy (id = "search-text")
    private WebElement searchInput;

    @FindBy (id = "search-submit")
    private WebElement searchSubmit;

    File file = new File("src/main/resources/login.txt");
    File logFile;
    private String user;
    private String password;
    private WebDriver driver;
    WebDriverWait hold;

    public void SeleniumTest() throws FileNotFoundException {
    }

    @Before
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        hold =  new WebDriverWait(driver, 10);
    }
    public void read() throws FileNotFoundException {
        Scanner br = new Scanner(file).useDelimiter(";");
        if (br.hasNext()) {
            user = br.next();
            }
        if (br.hasNext()) {
            password = br.next();
        }
        br.close();
    }

    public void logToFile(String log) throws IOException {
        String today = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());;
        String logTime = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Calendar.getInstance().getTime());;
        logFile = new File ("src/main/resources/" + today + " log.txt");
        PrintWriter pw = new PrintWriter(logFile);
        if (logFile.canWrite()) {
            pw.write(logTime + " " + log);
        }
        else {
            logFile = new File ("src/main/resouces/" + today + " log.txt");
            pw.write(logTime + " " + log);
        }
    }

    public void login() throws IOException {
        hold.until(ExpectedConditions.elementToBeClickable(loginButtonForm));
        loginButtonForm.click();
        username.sendKeys(user);
        pass.sendKeys(password);
        loginButtonConfirm.click();
    }

    public void search (String query) {
        searchInput.sendKeys(query);
        searchSubmit.click();
    }

    public void logout() {
        logoutButton.click();
    }

    @Test
    public void testLogin() throws IOException {
        driver.get("https://rutracker.org");
        logToFile("Enter a website");
        read();
        logToFile("Read the file with credentials");
        login();
        logToFile("Enter login and password");
        Assert.assertEquals(verify.getText(), user);
        logToFile("Check if login was successful");
        hold.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logToFile("Wait for logout button to be clickable");
        logout();
        logToFile("Logout");
    }

    @Test
    public void testLoginSearch() throws IOException {
        driver.get("https://rutracker.org");
        logToFile("Enter a website");
        read();
        logToFile("Read the file with credentials");
        login();
        logToFile("Enter login and password");
        search("The infestation");
        logToFile("Enter a query in a search field");
        hold.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logToFile("Wait for logout button to be clickable");
        logout();
        logToFile("Logout");
    }

    @After
    public void close() {
        driver.close();
    }
}
