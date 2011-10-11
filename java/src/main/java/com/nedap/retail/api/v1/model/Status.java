package com.nedap.retail.api.v1.model;

public class Status {

    private Device device;
    /**
     * @deprecated
     */
    private Reader reader;

    public Device getDevice() {
        return device;
    }

    /**
     * @deprecated
     */
    public Reader getReader() {
        return reader;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(device.toString());
        return result.toString();
    }

}
