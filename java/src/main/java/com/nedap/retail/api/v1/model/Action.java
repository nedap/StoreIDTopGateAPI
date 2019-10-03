package com.nedap.retail.api.v1.model;

public class Action {

    private final String action;
    private final Integer count;
    private final Integer onTime;
    private final Integer offTime;
    private final Integer lightsHoldTime;

    public Action(
            final String action, final Integer count, final Integer onTime, final Integer offTime,
            final Integer lightsHoldTime
    ) {
        this.action = action;
        this.count = count;
        this.onTime = onTime;
        this.offTime = offTime;
        this.lightsHoldTime = lightsHoldTime;
    }

    public String getAction() {
        return action;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getOnTime() {
        return onTime;
    }

    public Integer getOffTime() {
        return offTime;
    }

    public Integer getLightsHoldTime() {
        return lightsHoldTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Action=%s, count=%d, onTime=%d, offTime=%d, lightsHoldTime=%d", action, count, onTime, offTime,
                lightsHoldTime
        );
    }
}
