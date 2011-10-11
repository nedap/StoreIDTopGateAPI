package com.nedap.retail.api.v1.model;

/**
 * @deprecated
 */
public class Reader {

    private String hardwareVersion;
    private String softwareVersion;
    private String model;
    private String connectedAntenna;
    private String temperature;

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public String getModel() {
        return model;
    }

    public String getConnectedAntenna() {
        return connectedAntenna;
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("hardware version = ");
        result.append(this.hardwareVersion);
        result.append("\n");
        result.append("software version = ");
        result.append(this.softwareVersion);
        result.append("\n");
        result.append("model = ");
        result.append(this.model);
        result.append("\n");
        result.append("connected antenna = ");
        result.append(this.connectedAntenna);
        result.append("\n");
        result.append("temperature = ");
        result.append(this.temperature);
        result.append("\n");
        return result.toString();
    }
}
