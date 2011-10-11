package com.nedap.retail.api.v1.model;

import java.util.List;

public class SubscriptionList {

    private List<Subscription> subscriptions;

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(subscriptions.size());
        result.append(" subscription(s):\n");
        for (Subscription s : subscriptions) {
            result.append(s.toString());
            result.append("\n");
        }
        return result.toString();
    }

}
