package com.nedap.retail.api.v1.model;

public class Status {

    private final Device device;

    public Status(final Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    @Override
    public String toString() {
        return device.toString();
    }
}
