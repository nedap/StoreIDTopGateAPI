package com.nedap.retail.api.v1.tester;

import org.eclipse.jetty.server.Server;

/**
 * Wrapper for the Jetty web server
 */
public class EventsServer implements Runnable {
    /**
     * Jetty web server instance
     */
    private Server server;
    /**
     * The TCP port number to run on
     */
    private int portNumber;
    /**
     * The handler to handle requests
     */
    private EventsServerRequestHandler requestHandler;
    /**
     * How to handle events: RAW = display raw event, EPCCOUNT = count unique
     * EPCs, EPCLOG = write events to CSV file
     */
    public enum MODE {RAW, EPCCOUNT, EPCLOG};
    
    /**
     * Constructor
     * @param portNumber The TCP port number to run on
     */
    public EventsServer(int portNumber) {
        this.portNumber = portNumber;
        requestHandler = new EventsServerRequestHandler();
        requestHandler.setMode(MODE.RAW);
    }

    /**
     * Creates an instance of the web server and starts it
     * @throws Exception 
     */
    public void run() {
        if (!isRunning()) {
            server = new Server(portNumber);
            server.setHandler(requestHandler);
            try {
                server.start();
                server.join();
            } catch (Exception e) {
                System.out.println("Error starting Jetty: " + e.getMessage());
            }
        }
    }
    
    /**
     * Stops the web server
     * @throws Exception 
     */
    public void stop() {
        if (!isRunning() && (server != null)) {
            try {
                server.stop();
                server.destroy();
            } catch (Exception e) {
                System.out.println("Error stopping Jetty: " + e.getMessage());
            }
        }
    }
    
    /**
     * @return True when the server is running
     */
    public boolean isRunning() {
        if (server==null) {
            return false;
        }
        return server.isRunning();
    }
    
    /**
     * 
     * @return 
     */
    public void setMode(MODE mode) {
        requestHandler.setMode(mode);
    }
}
