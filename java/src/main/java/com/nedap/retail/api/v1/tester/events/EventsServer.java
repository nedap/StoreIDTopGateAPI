package com.nedap.retail.api.v1.tester.events;

import java.io.Closeable;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;

/**
 * Wrapper for the Jetty web server
 */
public class EventsServer implements Runnable, Closeable {
    /**
     * Jetty web server instance
     */
    private final Server server;

    /**
     * The handler to handle requests
     */
    private final Handler requestHandler;

    /**
     * Constructor
     * @param requestHandler The requesthandler to use.
     */
    public EventsServer(final Handler requestHandler, final Server server) {
        this.requestHandler = requestHandler;
        this.server = server;
    }

    /**
     * Creates an instance of the web server and starts it
     */
    public void run() {
        if (!server.isRunning()) {
            server.setHandler(requestHandler);
            try {
                server.start();
                server.join();
            } catch (final Exception exception) {
                System.out.println("Error starting Jetty: " + exception.getMessage());
            }
        }
    }
    
    /**
     * Stops the web server
     */
    public void close() {
        if (server.isRunning()) {
            try {
                server.stop();
                server.destroy();
            } catch (final Exception exception) {
                System.out.println("Error stopping Jetty: " + exception.getMessage());
            }
        }
    }
}
