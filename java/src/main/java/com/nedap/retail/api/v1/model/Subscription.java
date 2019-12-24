package com.nedap.retail.api.v1.model;

import com.google.gson.annotations.SerializedName;

public class Subscription {

    private final Integer id;
    private final String specname;
    @SerializedName("notification_uri")
    private final String notificationUri;
    @SerializedName("extern_ref")
    private final String externRef;
    private final int lease;

    public Subscription(
            final Integer id, final String specname, final String notification_uri, final String extern_ref,
            final int lease
    ) {
        this.id = id;
        this.specname = specname;
        this.notificationUri = notification_uri;
        this.externRef = extern_ref;
        this.lease = lease;
    }

    public Integer getId() {
        return id;
    }

    public String getSpecname() {
        return specname;
    }

    public String getNotificationUri() {
        return notificationUri;
    }

    public String getExternRef() {
        return externRef;
    }

    public int getLease() {
        return lease;
    }
    
    @Override
    public String toString() {
        return String.format(
                "id = %d%nspecname = %s%nnotification uri = %s%nexternal reference = %s%nlease time = %s%n minutes%n",
                this.id, this.specname, this.notificationUri, this.externRef, this.lease
        );
    }
}
