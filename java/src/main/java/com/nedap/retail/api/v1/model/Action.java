package com.nedap.retail.api.v1.model;

public class Action {
    private String action;
    private int count;
    
    public Action(String action, int count) {
        this.action = action;
        this.count = count;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public int getCount() {
        return this.count;
    }
}
