package com.nedap.retail.api.v1.model;

public class Subscription {

    private Integer id;
    private String specname;
    private String notification_uri;
    private String extern_ref;
    private int lease;

    public Subscription(final Integer id, final String specname,
            final String notification_uri, final String extern_ref,
            final int lease) {
        this.id = id;
        this.specname = specname;
        this.notification_uri = notification_uri;
        this.extern_ref = extern_ref;
        this.lease = lease;
    }

    public Integer getId() {
        return id;
    }

    public String getSpecname() {
        return specname;
    }

    public String getNotificationUri() {
        return notification_uri;
    }

    public String getExternRef() {
        return extern_ref;
    }

    public int getLease() {
        return lease;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("id = ");
        result.append(this.id);
        result.append("\n");
        result.append("specname = ");
        result.append(this.specname);
        result.append("\n");
        result.append("notification uri = ");
        result.append(this.notification_uri);
        result.append("\n");
        result.append("external reference = ");
        result.append(this.extern_ref);
        result.append("\n");
        result.append("lease time = ");
        result.append(this.lease);
        result.append(" minutes\n");
        return result.toString();
    }
}
