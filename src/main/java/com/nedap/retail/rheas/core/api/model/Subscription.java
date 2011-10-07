package com.nedap.retail.rheas.core.api.model;

/**
 * Subscription information.
 * 
 * @author Jasper Kremer
 */
public class Subscription {

    public Integer id;
    public String specname;
    public String notification_uri;
    public String extern_ref;
    /**
     * Amount lease time in minutes.
     */
    public int lease;

    public Subscription(final Integer id, final String specname, final String notification_uri,
            final String extern_ref, final int lease) {
        this.id = id;
        this.specname = specname;
        this.notification_uri = notification_uri;
        this.extern_ref = extern_ref;
        this.lease = lease;
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
