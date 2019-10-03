package com.nedap.retail.api.v1.model;

public class Device {

    private final String serial;
    private final String hardwareVersion;
    private final String softwareVersion;
    private final String description;
    private final String modelDescription;
    private final String modelName;
    private final String modelNumber;
    private final String modelURL;
    private final OperationalStatus operationalStatus;
    private final String operationalMessage;
    private final String role;
    private final String systemId;

    public Device(
            final String serial, final String hardwareVersion, final String softwareVersion, final String description,
            final String modelDescription, final String modelName, final String modelNumber, final String modelURL,
            final OperationalStatus operationalStatus, final String operationalMessage, final String role,
            final String systemId
    ) {
        this.serial = serial;
        this.hardwareVersion = hardwareVersion;
        this.softwareVersion = softwareVersion;
        this.description = description;
        this.modelDescription = modelDescription;
        this.modelName = modelName;
        this.modelNumber = modelNumber;
        this.modelURL = modelURL;
        this.operationalStatus = operationalStatus;
        this.operationalMessage = operationalMessage;
        this.role = role;
        this.systemId = systemId;
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

    public String getRole() {
        return role;
    }

    public String getSystemId() {
        return systemId;
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
        result.append("firmware version = ");
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
        result.append("\n");
        result.append("role = ");
        result.append(this.role);
        result.append("\n");
        result.append("system id = ");
        result.append(this.systemId);
        return result.toString();
    }

}
