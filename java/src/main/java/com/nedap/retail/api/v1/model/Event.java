package com.nedap.retail.api.v1.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * API event
 */
public class Event {
    private final String id;
    private final String type;
    private final long occurTime;
    private final String direction;
    @SerializedName("extern_ref")
    private final String externRef;
    private final List<EventEpc> epcList;

    public Event(
            final String id, final String type, final long occurTime, final String direction, final String externRef,
            final EventEpc ... eps
    ) {
        this.id = id;
        this.type = type;
        this.occurTime = occurTime;
        this.direction = direction;
        this.externRef = externRef;
        this.epcList = Arrays.asList(eps);
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public long getOccurTime() {
        return occurTime;
    }

    public String getDirection() {
        return direction;
    }

    public String getExternRef() {
        return externRef;
    }

    public List<EventEpc> getEpcList() {
        return epcList;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
