package com.nedap.retail.api.v1.model;

public class Status {

    private Device device;

    public Device getDevice() {
        return device;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(device.toString());
        return result.toString();
    }

}
