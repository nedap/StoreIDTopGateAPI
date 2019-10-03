package com.nedap.retail.api.v1.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionList {

    private final List<Subscription> subscriptions;

    public SubscriptionList(final Subscription ... subscriptions) {
        this.subscriptions = Arrays.asList(subscriptions);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    @Override
    public String toString() {
        return String.format(
                "%d subscription(s):%n%s", subscriptions.size(),
                subscriptions.stream()
                        .map(Subscription::toString)
                        .collect(Collectors.joining("\n"))
        );
    }
}
