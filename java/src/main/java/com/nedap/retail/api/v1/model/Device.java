package com.nedap.retail.api.v1.model;

import java.util.List;

public class Device {

    /**
     * @deprecated
     */
    private String physicalId;
    private String serial;
    private String hardwareVersion;
    private String softwareVersion;
    private String description;
    private String modelDescription;
    private String modelName;
    private String modelNumber;
    private String modelURL;
    private OperationalStatus operationalStatus;
    private String operationalMessage;
    /**
     * @deprecated
     */
    private String memoryUsage;
    /**
     * @deprecated
     */
    private List<String> cpuLoad;
    /**
     * @deprecated
     */
    private Uptime uptime;

    /**
     * @deprecated
     */
    public String getPhysicalId() {
        return physicalId;
    }

    public String getSerial() {
        return serial;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public String getDescription() {
        return description;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getModelURL() {
        return modelURL;
    }

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }

    public String getOperationalMessage() {
        return operationalMessage;
    }

    /**
     * @deprecated
     */
    public String getMemoryUsage() {
        return memoryUsage;
    }

    /**
     * @deprecated
     */
    public List<String> getCpuLoad() {
        return cpuLoad;
    }

    /**
     * @deprecated
     */
    public Uptime getUptime() {
        return uptime;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("serial = ");
        result.append(this.serial);
        result.append("\n");
        result.append("hardware version = ");
        result.append(this.hardwareVersion);
        result.append("\n");
        result.append("software version = ");
        result.append(this.softwareVersion);
        result.append("\n");
        result.append("description = ");
        result.append(this.description);
        result.append("\n");
        result.append("model description = ");
        result.append(this.modelDescription);
        result.append("\n");
        result.append("model name = ");
        result.append(this.modelName);
        result.append("\n");
        result.append("model number = ");
        result.append(this.modelNumber);
        result.append("\n");
        result.append("model URL = ");
        result.append(this.modelURL);
        result.append("\n");
        if (this.operationalStatus!=null) {
            result.append("operational status = ");
            result.append(this.operationalStatus.toString());
            result.append("\n");
        }
        result.append("operational message = ");
        result.append(this.operationalMessage);
        return result.toString();
    }

}
