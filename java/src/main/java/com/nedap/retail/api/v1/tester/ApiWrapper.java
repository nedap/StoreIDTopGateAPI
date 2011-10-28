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
     * @param url base URL for all API calls
     */
    public ApiWrapper(String baseUrl) {
        // make sure baseUrl does not end with a slash
        if (baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl.substring(0, baseUrl.length()-1);
        } else {
            this.baseUrl = baseUrl;
        }
    }
    
    public Status getStatus() throws Exception {
        String httpResult = doHttpRequest("/status");
        Gson gson = new Gson();
        return (Status)gson.fromJson(httpResult, Status.class);
    }

    public SpecList getSpecs() throws Exception {
        String httpResult = doHttpRequest("/service/events/specs");
        Gson gson = new Gson();
        return (SpecList)gson.fromJson(httpResult, SpecList.class);
    }
    
    public Spec createSpec(Spec spec) throws Exception {
        String httpResult = doHttpRequest("/service/events/specs", "POST", spec);
        Gson gson = new Gson();
        return (Spec)gson.fromJson(httpResult, Spec.class);
    }

    public Spec getSpec(Integer id) throws Exception {
        String httpResult = doHttpRequest("/service/events/specs/" + id);
        Gson gson = new Gson();
        return (Spec)gson.fromJson(httpResult, Spec.class);
    }

    public void deleteSpec(Integer id) throws Exception {
        String httpResult = doHttpRequest("/service/events/specs/" + id, "DELETE");
    }

    public Spec updateSpec(Spec spec) throws Exception {
        String httpResult = doHttpRequest("/service/events/specs/" + spec.getId(), "PUT", spec);
        Gson gson = new Gson();
        return (Spec)gson.fromJson(httpResult, Spec.class);
    }

    public SubscriptionList getSubscriptions() throws Exception {
        String httpResult = doHttpRequest("/service/events/subscriptions");
        Gson gson = new Gson();
        return (SubscriptionList)gson.fromJson(httpResult, SubscriptionList.class);
    }
    
    public Subscription createSubscription(Subscription subscription) throws Exception {
        String httpResult = doHttpRequest("/service/events/subscriptions", "POST", subscription);
        Gson gson = new Gson();
        return (Subscription)gson.fromJson(httpResult, Subscription.class);
    }

    public Subscription getSubscription(Integer id) throws Exception {
        String httpResult = doHttpRequest("/service/events/subscriptions/" + id);
        Gson gson = new Gson();
        return (Subscription)gson.fromJson(httpResult, Subscription.class);
    }

    public void deleteSubscription(Integer id) throws Exception {
        String httpResult = doHttpRequest("/service/events/subscriptions/" + id, "DELETE");
    }

    public Subscription updateSubscription(Subscription subscription) throws Exception {
        String httpResult = doHttpRequest("/service/events/subscriptions/" + subscription.getId(), "PUT", subscription);
        Gson gson = new Gson();
        return (Subscription)gson.fromJson(httpResult, Subscription.class);
    }

    public void sendAction(Action[] actions) throws Exception {
        String httpResult = doHttpRequest("/service/actions", "POST", actions);
    }

    public void testConnection() {
        try {
            String httpResult = doHttpRequest("/status");
            if (httpResult.length()>4) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Invalid response received");
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    private String doHttpRequest(String url, String requestMethod, Object data) throws Exception {
        Gson gson = new Gson();
        URL device = new URL(this.baseUrl + url);
        HttpURLConnection connection = (HttpURLConnection) device.openConnection();
        if (requestMethod.equals("GET")) {
            // is the default, do nothing
        } else if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod(requestMethod);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(gson.toJson(data));
            out.close();
        } else if (requestMethod.equals("DELETE")) {
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod(requestMethod);
            connection.connect();
        }
        System.out.println("Response code = " + connection.getResponseCode());
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = inputBuffer.readLine()) != null ) {
            result.append(line);
        }
        inputBuffer.close();
        return result.toString();
    }
    
    private String doHttpRequest(String url, String requestMethod) throws Exception {
        return doHttpRequest(url, requestMethod, null);
    }

    private String doHttpRequest(String url) throws Exception {
        return doHttpRequest(url, "GET", null);
    }
}
