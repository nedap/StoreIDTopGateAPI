package com.nedap.retail.api.v1.tester;

import com.nedap.retail.api.v1.model.Subscription;
import java.util.TimerTask;

/**
 * Task to periodically renew subscription
 */
public class RenewSubscriptionTask extends TimerTask {
    /**
     * Reference to the API
     */
    private ApiWrapper api;
    /**
     * The subscription to renew
     */
    private Subscription sub;
    
    public RenewSubscriptionTask(ApiWrapper api, Subscription sub) {
        this.api = api;
        this.sub = sub;
    }

    public void run() {
        System.out.println("Updating subscription");
        try {
            api.updateSubscription(sub);
        } catch (Exception e) {
            System.out.println("Error updating subscription");
        }
    }
}
