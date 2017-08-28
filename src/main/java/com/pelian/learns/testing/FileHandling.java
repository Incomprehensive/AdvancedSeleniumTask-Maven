package com.pelian.learns.testing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by Anton Pelianski on 08.07.2017.
 */
public class FileHandling {

    public String user;

    public String password;

    protected WebDriverWait hold;

    File file = new File("src/main/resources/login.txt");

    File logFile;

    File results = new File("src/main/resources/SearchResults.txt");

    /**
     * Method to read credentials from file
     *
     * @throws FileNotFoundException
     */
    public void read() throws FileNotFoundException {
        // Read a file with credentials to ger username and password for login method
        Scanner br = new Scanner(file).useDelimiter(";");
        if (br.hasNext()) {
            user = br.next();
        }
        if (br.hasNext()) {
            password = br.next();
        }
        br.close();
    }

    /**
     * Method to log our actions into a log file
     *
     * @param log
     * @throws IOException
     */
    public void logToFile(String log) throws IOException {
        String today = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
        String logTime = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        logFile = new File("src/main/resources/" + today + " log.txt");
        PrintWriter pw = new PrintWriter(logFile);
        if (logFile.canWrite()) {
            pw.write(logTime + " " + log);
        } else {
            logFile = new File("src/main/resources/" + today + " log.txt");
            pw.write(logTime + " " + log);
        }
    }

    /**
     * Method to write search results to file
     *
     * @throws FileNotFoundException
     */
    @SuppressWarnings("Since15")
    public void resultsToFile() throws FileNotFoundException {
        File results = new File("src/main/resources/SearchResults.txt");
        ArrayList<WebElement> matches = new ArrayList<>();
        ArrayList<String> arrayString = new ArrayList<>();
        PrintWriter writer = new PrintWriter(results);
        for (WebElement match : matches) {
            arrayString.add(match.toString());
            int index = arrayString.indexOf(this);
            writer.write(arrayString.get(index));
        }
    }
}