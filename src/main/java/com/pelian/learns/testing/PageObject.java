package com.pelian.learns.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;

    protected WebDriverWait hold;

    public PageObject (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        hold =  new WebDriverWait(driver, 5);
    }
}