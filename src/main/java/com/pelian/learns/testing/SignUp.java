package com.pelian.learns.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class SignUp extends PageObject {

    @FindBy(xpath = "//input[@name = 'login_username']")
    protected WebElement usrTextField;

    @FindBy(xpath = "//input[@name = 'login_password']")
    protected WebElement passTextField;

    @FindBy(xpath = "//span[@class = 'a-like bold']")
    protected WebElement loginButtonForm;

    @FindBy(xpath = "//input[@name = 'login']")
    protected WebElement loginButtonSubmit;

    @FindBy(xpath = "//div[@class = 'logged-in-as-wrap']/a[2]")
    protected WebElement logoutButton;

    public SignUp(WebDriver driver) {
        super(driver);
    }

    /**
     * Method that logs you in
     *
     * @throws IOException
     */
    public void login(String user, String password) throws IOException {
        hold.until(ExpectedConditions.elementToBeClickable(loginButtonForm));
        loginButtonForm.click();
        usrTextField.sendKeys(user);
        passTextField.sendKeys(password);
        loginButtonSubmit.click();
    }

}
