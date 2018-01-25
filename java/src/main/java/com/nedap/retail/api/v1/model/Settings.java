package com.nedap.retail.api.v1.model;

public class Settings {
    private Boolean readerEnabled = null;
    private Boolean lightsEnabled = null;
    private Boolean buzzerEnabled = null;
    private Integer buzzerVolume = null;

    public Settings() {
    }

    public Settings(final Boolean readerEnabled, final Boolean lightsEnabled, final Boolean buzzerEnabled, final Integer buzzerVolume) {
        this.readerEnabled = readerEnabled;
        this.lightsEnabled = lightsEnabled;
        this.buzzerEnabled = buzzerEnabled;
        this.buzzerVolume = buzzerVolume;
    }

    public Boolean getReaderEnabled() {
        return readerEnabled;
    }

    public void setReaderEnabled(Boolean readerEnabled) {
        this.readerEnabled = readerEnabled;
    }

    public Boolean getLightsEnabled() {
        return lightsEnabled;
    }

    public void setLightsEnabled(Boolean lightsEnabled) {
        this.lightsEnabled = lightsEnabled;
    }

    public Boolean getBuzzerEnabled() {
        return buzzerEnabled;
    }

    public void setBuzzerEnabled(Boolean buzzerEnabled) {
        this.buzzerEnabled = buzzerEnabled;
    }

    public Integer getBuzzerVolume() {
        return buzzerVolume;
    }

    public void setBuzzerVolume(Integer buzzerVolume) {
        this.buzzerVolume = buzzerVolume;
    }

    @Override
    public String toString() {
        return "readerEnabled: " + readerEnabled
                + ", lightsEnabled: " + lightsEnabled
                + ", buzzerEnabled: " + buzzerEnabled
                + ", buzzerVolume: " + buzzerVolume;
                
    }
}
