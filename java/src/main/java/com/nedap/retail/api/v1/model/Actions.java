package com.nedap.retail.api.v1.model;

public class Actions {
    private final Action[] actions;
    
    public Actions(final Action ... actions) {
        this.actions = actions;
    }

    public Action[] getActions() {
        return this.actions;
    }
}
