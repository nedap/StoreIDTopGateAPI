package com.nedap.retail.api.v1.tester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Handles a socket connection from the API (HTTP, Content transfer encoding is
 * chunked) and prints the data to System.out
 */
public class EventsServerConnectionHandler implements Runnable {
    /**
     * For reading from the socket
     */
    private BufferedReader in;
    /**
     * For writing to the socket
     */
    private PrintWriter out;
    /**
     * The socket
     */
    private Socket client;

    public EventsServerConnectionHandler(Socket client) {
        this.client = client;
        try {
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void run() {
        String message = "";
        boolean readingHeader = true;
        boolean reading = true;
        try {
            while(reading) {
                // wait for input from client
                message = in.readLine();
                if (message==null) {
                    // client is disconnected
                    reading = false;
                } else {
                    if (readingHeader) {
                        // empty line means end of HTTP headers
                        if (message.length()==0) {
                            readingHeader = false;
                        }
                    } else {
                        if (message.equals("0")) {
                            // chunk length 0 means end of request
                            reading = false;
                        } else {
                            System.out.println(message);
                        }
                    }
                }
            }
            // send HTTP reply
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("X-Powered-By: Nedap Retail");
            out.println("Connection: close");
            out.println("Content-Length: 3");
            out.println("");
            out.println("Ok");
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                
            }
        }
    }
}
