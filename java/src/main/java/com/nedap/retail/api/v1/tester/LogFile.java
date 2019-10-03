package com.nedap.retail.api.v1.tester;

import java.io.FileWriter;
import java.util.Date;

/**
 *
 * @author bas.jansen
 */
public class LogFile {
    public static String FILENAME = null;
    
    public static void write(final String message) {
        if (FILENAME == null) {
            FILENAME = "log_" + new Date().toString().replace(" ", "_").replace(":","") + ".log";
        }
        try (final FileWriter logfile = new FileWriter(FILENAME, true)){
            logfile.write(message + "\n");
        } catch (final Exception e) {
            System.out.println("Could not write to logfile!");
        }
    }
}
