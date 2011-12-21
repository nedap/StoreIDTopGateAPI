package com.nedap.retail.api.v1.model;

public class Action {

    private String action;
    private Integer count;
    private Integer onTime;
    private Integer offTime;
    private Integer lightsHoldTime;

    public Action(final String action, final Integer count, final Integer onTime, final Integer offTime, final Integer lightsHoldTime) {
        this.action = action;
        this.count = count;
        this.onTime = onTime;
        this.offTime = offTime;
        this.lightsHoldTime = lightsHoldTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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

    public void setoffTime(Integer offTime) {
        this.offTime = offTime;
    }

    public Integer getLightsHoldTime() {
        return lightsHoldTime;
    }

    public void setLightsHoldTime(Integer lightsHoldTime) {
        this.lightsHoldTime = lightsHoldTime;
    }

    @Override
    public String toString() {
        return "Action=" + action + ", count=" + count + ", onTime=" + onTime + ", offTime=" + offTime + ", lightsHoldTime=" + lightsHoldTime;
    }
}
