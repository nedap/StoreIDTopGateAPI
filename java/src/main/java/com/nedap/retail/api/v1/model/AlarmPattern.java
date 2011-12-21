package com.nedap.retail.api.v1.model;

public class AlarmPattern {
    private Integer onTime;
    private Integer offTime;
    private Integer lightsHoldTime;
    private Integer count;

    public AlarmPattern() {
    }
    
    public AlarmPattern(final Integer onTime, final Integer offTime, final Integer lightsHoldTime, final Integer count) {
        this.onTime = onTime;
        this.offTime = offTime;
        this.lightsHoldTime = lightsHoldTime;
        this.count = count;
    }
    
    public Integer getOnTime() {
        return onTime;
    }

    public void setOnTime(Integer onTime) {
        this.onTime = onTime;
    }

    public Integer getOffTime() {
        return offTime;
    }

    public void setOffTime(Integer offTime) {
        this.offTime = offTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLightsHoldTime() {
        return lightsHoldTime;
    }

    public void setLightsHoldTime(Integer lightsHoldTime) {
        this.lightsHoldTime = lightsHoldTime;
    }
    
    @Override
    public String toString() {
        return "onTime: " + onTime
                + ", offTime: " + offTime
                + ", count: " + count
                + ", lightsHoldTime: " + lightsHoldTime;
    }
}
