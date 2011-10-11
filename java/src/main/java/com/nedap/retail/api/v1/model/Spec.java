package com.nedap.retail.api.v1.model;

public class Spec {

    private Integer id;
    private String name;
    private String[] event_types;

    public Spec(final Integer id, final String name,
            final String[] event_types) {
        this.id = id;
        this.name = name;
        this.event_types = event_types;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getEventTypes() {
        return event_types;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("id = ");
        result.append(this.id);
        result.append("\n");
        result.append("name = ");
        result.append(this.name);
        result.append("\n");
        result.append("event types = ");
        if (event_types!=null) {
            for (String e : event_types) {
                result.append(e);
                result.append(" ");
            }
        }
        return result.toString();
    }
}
