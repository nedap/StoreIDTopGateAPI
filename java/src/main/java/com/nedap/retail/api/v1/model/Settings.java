package com.nedap.retail.api.v1.model;

public class Settings {
    private final Boolean readerEnabled;
    private final Boolean lightsEnabled;
    private final Boolean buzzerEnabled;
    private final Integer buzzerVolume;

    public Settings() {
        this.readerEnabled = null;
        this.lightsEnabled = null;
        this.buzzerEnabled = null;
        this.buzzerVolume = null;
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

    public Settings setReaderEnabled(final Boolean readerEnabled) {
        return new Settings(readerEnabled, lightsEnabled, buzzerEnabled, buzzerVolume);
    }

    public Boolean getLightsEnabled() {
        return lightsEnabled;
    }

    public Settings setLightsEnabled(final Boolean lightsEnabled) {
        return new Settings(readerEnabled, lightsEnabled, buzzerEnabled, buzzerVolume);
    }

    public Boolean getBuzzerEnabled() {
        return buzzerEnabled;
    }

    public Settings setBuzzerEnabled(final Boolean buzzerEnabled) {
        return new Settings(readerEnabled, lightsEnabled, buzzerEnabled, buzzerVolume);
    }

    public Integer getBuzzerVolume() {
        return buzzerVolume;
    }

    public Settings setBuzzerVolume(final Integer buzzerVolume) {
        return new Settings(readerEnabled, lightsEnabled, buzzerEnabled, buzzerVolume);
    }

    @Override
    public String toString() {
        return String.format(
                "readerEnabled: %s, lightsEnabled: %s, buzzerEnabled: %s, buzzerVolume: %s", readerEnabled,
                lightsEnabled, buzzerEnabled, buzzerVolume
        );
    }
}
