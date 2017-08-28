package com.pelian.learns.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class SearchPage extends PageObject {

    @FindBy(xpath = "//input[@id = 'search-text']")
    protected WebElement searchInput;

    @FindBy (xpath = "//input[@id = 'search-submit']")
    protected WebElement searchSubmit;

    @FindBy(xpath = "//child::td[4]/div/a")
    protected ArrayList<WebElement> searchResults;

    protected String Query = "the infestation";

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void CommitSearch() {
        searchInput.sendKeys(Query);
        searchSubmit.click();
    }
    /**
     * Method to write search results to file
     *
     * @throws FileNotFoundException
     */
    public void resultsToFile(File path) throws FileNotFoundException {
        Iterator<WebElement> iterator = searchResults.iterator();
        PrintWriter pw = new PrintWriter(path);
        while (iterator.hasNext()) {
            pw.write("Result - " + iterator.next().getText());
        }
    }
}
