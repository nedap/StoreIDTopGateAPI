package com.nedap.retail.api.v1.tester;

import java.io.FileWriter;
import java.util.Date;

/**
 *
 * @author bas.jansen
 */
public class LogFile {
    public static String filename = null;
    
    public static void write(String message) {
        if (filename==null) {
            Date d = new Date();
            filename = "log_" + d.toString().replace(" ", "_").replace(":","") + ".log";
        }
        try {
            FileWriter logfile = new FileWriter(filename, true);
            logfile.write(message + "\n");
            logfile.close();
        } catch (Exception e) {
            System.out.println("Could not write to logfile!");
        }
    }
}
