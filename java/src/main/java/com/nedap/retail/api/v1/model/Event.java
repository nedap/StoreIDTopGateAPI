package com.nedap.retail.api.v1.model;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * API event
 */
public class Event {
    public String id;
    public String type;
    public long occurTime;
    public String direction;
    public String extern_ref;
    public List<EventEpc> epcList = new ArrayList<EventEpc>();
    
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
