package com.nedap.retail.rheas.core.api.model;

/**
 * Status object contains status information about {@link Device} and {@link Reader}.
 * 
 * @author Dusko Vesin
 * 
 */
public class Status {

    private Device device;
    private Reader reader;

    public Device getDevice() {
        return device;
    }

    public Reader getReader() {
        return reader;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("=== DEVICE ===\n");
        result.append(device.toString());
        result.append("\n");
        result.append("=== READER ===\n");
        result.append(reader.toString());
        return result.toString();
    }

}
