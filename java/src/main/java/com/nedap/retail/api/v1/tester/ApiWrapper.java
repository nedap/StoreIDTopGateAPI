package com.nedap.retail.api.v1.tester;

import com.google.gson.Gson;
import com.nedap.retail.api.v1.model.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
        
/**
 * A wrapper for easy access to API calls of !D Top and !D Hand
 */
public class ApiWrapper {
    private String baseUrl;
    
    /**
     * Constructor
     * @param baseUrl base URL for all API calls
     */
    public ApiWrapper(final String baseUrl) {
        // make sure baseUrl does not end with a slash
        if (baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl.substring(0, baseUrl.length()-1);
        } else {
            this.baseUrl = baseUrl;
        }
    }
    
    public Status getStatus() throws Exception {
        return new Gson().fromJson(doHttpRequest("/status"), Status.class);
    }

    public SpecList getSpecs() throws Exception {
        return new Gson().fromJson(doHttpRequest("/service/events/specs"), SpecList.class);
    }
    
    public Spec createSpec(final Spec spec) throws Exception {
        return new Gson().fromJson(doHttpRequest("/service/events/specs", "POST", spec), Spec.class);
    }

    public Spec getSpec(final Integer id) throws Exception {
        return new Gson().fromJson(doHttpRequest("/service/events/specs/" + id), Spec.class);
    }

    public void deleteSpec(final Integer id) throws Exception {
        doHttpRequest("/service/events/specs/" + id, "DELETE");
    }

    public Spec updateSpec(final Spec spec) throws Exception {
        return new Gson().fromJson(doHttpRequest("/service/events/specs/" + spec.getId(), "PUT", spec), Spec.class);
    }

    public SubscriptionList getSubscriptions() throws Exception {
        return new Gson().fromJson(doHttpRequest("/service/events/subscriptions"), SubscriptionList.class);
    }
    
    public Subscription createSubscription(final Subscription subscription) throws Exception {
        return new Gson().fromJson(
                doHttpRequest("/service/events/subscriptions", "POST", subscription), Subscription.class
        );
    }

    public Subscription getSubscription(final Integer id) throws Exception {
        return new Gson().fromJson(doHttpRequest("/service/events/subscriptions/" + id), Subscription.class);
    }

    public void deleteSubscription(final Integer id) throws Exception {
        doHttpRequest("/service/events/subscriptions/" + id, "DELETE");
    }

    public Subscription updateSubscription(final Subscription subscription) throws Exception {
        return new Gson().fromJson(
                doHttpRequest(
                        "/service/events/subscriptions/" + subscription.getId(), "PUT", subscription
                ), Subscription.class
        );
    }

    public void sendActions(final Actions actions) throws Exception {
        doHttpRequest("/service/actions", "POST", actions);
    }

    public Settings getSettings() throws Exception {
        return new Gson().fromJson(doHttpRequest("/service/settings"), Settings.class);
    }
    
    public void updateSettings(final Settings settings) throws Exception {
        doHttpRequest("/service/settings", "PUT", settings);
    }

    public void heartbeat() throws Exception {
        doHttpRequest("/heartbeat", "GET");
    }

    public void testConnection() throws Exception {
        final String httpResult = doHttpRequest("/status");
        if (httpResult.length() > 4) {
            System.out.println("Connection OK");
        } else {
            System.out.println("Invalid response received");
        }
    }

    private String doHttpRequest(final String url, final String requestMethod, final Object data) throws Exception {
        final HttpURLConnection connection = (HttpURLConnection) new URL(this.baseUrl + url).openConnection();
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod(requestMethod);
            try (final OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
                out.write(new Gson().toJson(data));
            }
        } else if (requestMethod.equals("DELETE")) {
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod(requestMethod);
            connection.connect();
        }
        System.out.println("Response code = " + connection.getResponseCode());
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            final StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
    }
    
    private String doHttpRequest(final String url, final String requestMethod) throws Exception {
        return doHttpRequest(url, requestMethod, null);
    }

    private String doHttpRequest(final String url) throws Exception {
        return doHttpRequest(url, "GET", null);
    }
}
