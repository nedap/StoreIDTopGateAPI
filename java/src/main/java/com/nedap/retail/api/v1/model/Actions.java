package com.nedap.retail.api.v1.model;

public class Actions {
    private Action[] actions;
    
    public Actions(Action[] actions) {
        this.actions = actions;
    }

    public Actions(int count) {
        this.actions = new Action[count];
    }

    public Action[] getActions() {
        return this.actions;
    }
    
    public void add(int index, Action action) {
        this.actions[index] = action;
    }
}
