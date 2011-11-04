package com.nedap.retail.api.v1.tester;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Very simple HTTP server to receive API events, uses
 * EventsServerConnectionHandler to handle individual connections
 */
public class EventsServer implements Runnable {
    /**
     * TCP port to listen on
     */
    private int tcpPort;
    
    public EventsServer(int tcpPort) {
        this.tcpPort = tcpPort;
    }
    
    public void run() {
        // create TCP server
        ServerSocket server = null;
        try {
            server = new ServerSocket(tcpPort);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        
        // wait for incoming connections
        System.out.println("Waiting for events on port " + tcpPort);
        Socket client = null;
        while(true) {
            try {
                client = server.accept();
            } catch (IOException e) {
                System.out.println(e);
            }
            // start a new thread to handle this client
            Thread t = new Thread(new EventsServerConnectionHandler(client));
            t.start();
        }
    }
}
