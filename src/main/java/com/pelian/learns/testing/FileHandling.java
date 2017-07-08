package com.pelian.learns.testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by Anton Pelianski on 08.07.2017.
 */
public class FileHandling {
    public String user;
    public String password;
    File file = new File("src/main/resources/login.txt");
    File logFile;

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
}
