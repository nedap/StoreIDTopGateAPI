package com.nedap.retail.api.v1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Epc info in an event
 */
public class EventEpc {
    private final long time;
    private final String epc;
    @SerializedName("eas_status")
    private final String easStatus;
    private final String group;

    public EventEpc(final long time, final String epc, final String easStatus, final String group) {
        this.time = time;
        this.epc = epc;
        this.easStatus = easStatus;
        this.group = group;
    }

    public long getTime() {
        return time;
    }

    public String getEpc() {
        return epc;
    }

    public String getEasStatus() {
        return easStatus;
    }

    public String getGroup() {
        return group;
    }
}
