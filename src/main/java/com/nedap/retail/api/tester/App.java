package com.nedap.retail.api.tester;

import com.nedap.retail.rheas.core.api.model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Store !D API tester for !D Top and !D Gate
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Store !D API tester for !D Top and !D Gate");
        
        if (args.length==0)
        {
            System.out.println("Please use URL of device as parameter, for example: http://localhost:8081");
            System.exit(0);
        }
        
        // instantiate API wrapper
        ApiWrapper api = new ApiWrapper(args[0]);
        
        boolean running = true;
        while(running) {
            // show menu
            System.out.println();
            System.out.println("------------------------------------------------------");
            System.out.println("0. Test connection");
            System.out.println("1. Show status");
            System.out.println("-- SPECS --                 -- SUBSCRIPTIONS --");
            System.out.println("2. Show all specs           7. Show all subscriptions");
            System.out.println("3. Create new spec          8. Create new subscription");
            System.out.println("4. Show a spec              9. Show a subscription");
            System.out.println("5. Update a spec            a. Update a subscription");
            System.out.println("6. Delete a spec            b. Delete a subscription");
            System.out.println("Please enter your choice and press Enter, or just Enter to exit.");
            
            // get choice
            BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = inputBuffer.readLine();
            } catch (IOException e) {
                System.exit(0);
            }
            
            // exit if no choice made
            if (input.length()==0) {
                System.exit(0);
            }
            
            // print line
            System.out.println("------------------------------------------------------");
            
            // check what choice has been made
            int keycode = input.codePointAt(0);
            switch(keycode) {
                case 48:    // 0
                    System.out.println("Test connection");
                    api.testConnection();
                    break;
                case 49:    // 1
                    System.out.println("Show status");
                    try {
                        System.out.println(api.getStatus().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 50:    // 2
                    System.out.println("Show specs");
                    try {
                        System.out.println(api.getSpecs().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 51:    // 3
                    System.out.println("Create new spec");
                    System.out.print("Enter a name: ");
                    String createSpecName = "Test";
                    try {
                        createSpecName = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.println("Which events?");
                    System.out.println("1 = rfid.tag.arrive");
                    System.out.println("2 = rfid.tag.depart");
                    System.out.println("3 = rfid.tag.arrive AND rfid.tag.depart");
                    String createSpecOptions = "3";
                    try {
                        createSpecOptions = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    String[] createSpecEvents;
                    if (createSpecOptions.equals("1")) {
                        createSpecEvents = new String[1];
                        createSpecEvents[0] = "rfid.tag.arrive";
                    } else if (createSpecOptions.equals("2")) {
                        createSpecEvents = new String[1];
                        createSpecEvents[0] = "rfid.tag.depart";
                    } else {
                        createSpecEvents = new String[2];
                        createSpecEvents[0] = "rfid.tag.arrive";
                        createSpecEvents[1] = "rfid.tag.depart";
                    }
                    Spec createSpec = new Spec(0, createSpecName, createSpecEvents);
                    try {
                        System.out.println(api.createSpec(createSpec).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 52:    // 4
                    System.out.println("Show a spec");
                    System.out.print("Enter an ID: ");
                    String showSpecId = "";
                    try {
                        showSpecId = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    try {
                        System.out.println(api.getSpec(Integer.parseInt(showSpecId)).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 53:    // 4
                    System.out.println("Update a spec");
                    System.out.print("Enter ID of spec to update: ");
                    String updateSpecId = "";
                    try {
                        updateSpecId = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("Enter a name: ");
                    String updateSpecName = "Test";
                    try {
                        updateSpecName = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.println("Which events?");
                    System.out.println("1 = rfid.tag.arrive");
                    System.out.println("2 = rfid.tag.depart");
                    System.out.println("3 = rfid.tag.arrive AND rfid.tag.depart");
                    String updateSpecOptions = "3";
                    try {
                        updateSpecOptions = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    String[] updateSpecEvents;
                    if (updateSpecOptions.equals("1")) {
                        updateSpecEvents = new String[1];
                        updateSpecEvents[0] = "rfid.tag.arrive";
                    } else if (updateSpecOptions.equals("2")) {
                        updateSpecEvents = new String[1];
                        updateSpecEvents[0] = "rfid.tag.depart";
                    } else {
                        updateSpecEvents = new String[2];
                        updateSpecEvents[0] = "rfid.tag.arrive";
                        updateSpecEvents[1] = "rfid.tag.depart";
                    }
                    Spec updateSpec = new Spec(Integer.parseInt(updateSpecId), updateSpecName, updateSpecEvents);
                    try {
                        System.out.println(api.updateSpec(updateSpec).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 54:    // 6
                    System.out.println("Delete a spec");
                    System.out.print("Enter an ID: ");
                    String deleteSpecId = "";
                    try {
                        deleteSpecId = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    try {
                        api.deleteSpec(Integer.parseInt(deleteSpecId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 55:    // 7
                    System.out.println("Show subscriptions");
                    try {
                        System.out.println(api.getSubscriptions().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 56:    // 8
                    System.out.println("Create new subscription");
                    System.out.print("Enter name of a spec: ");
                    String createSubscriptionSpecName = "";
                    try {
                        createSubscriptionSpecName = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("URI to send events to: ");
                    String createSubscriptionUri = "";
                    try {
                        createSubscriptionUri = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("External reference (optional): ");
                    String createSubscriptionExternRef = "";
                    try {
                        createSubscriptionExternRef = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("Requested lease time in minutes: ");
                    String createSubscriptionLease = "";
                    try {
                        createSubscriptionLease = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    Integer createSubscriptionLeaseTime = 0;
                    if (createSubscriptionLease.length()>0) {
                        createSubscriptionLeaseTime = Integer.parseInt(createSubscriptionLease);
                    }
                    Subscription createSubscription = new Subscription(0, createSubscriptionSpecName, createSubscriptionUri, createSubscriptionExternRef, createSubscriptionLeaseTime);
                    try {
                        System.out.println(api.createSubscription(createSubscription).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 57:    // 9
                    System.out.println("Show a subscription");
                    System.out.print("Enter an ID: ");
                    String showSubscriptionId = "";
                    try {
                        showSubscriptionId = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    try {
                        System.out.println(api.getSubscription(Integer.parseInt(showSubscriptionId)).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 97:    // a
                    System.out.println("Update a subscription");
                    System.out.print("Enter ID of subscription to update: ");
                    String updateSubscriptionId = "";
                    try {
                        updateSubscriptionId = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("Enter name of a spec: ");
                    String updateSubscriptionSpecName = "";
                    try {
                        updateSubscriptionSpecName = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("URI to send events to: ");
                    String updateSubscriptionUri = "";
                    try {
                        updateSubscriptionUri = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("External reference (optional): ");
                    String updateSubscriptionExternRef = "";
                    try {
                        updateSubscriptionExternRef = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    System.out.print("Requested lease time in minutes: ");
                    String updateSubscriptionLease = "";
                    try {
                        updateSubscriptionLease = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    Integer updateSubscriptionLeaseTime = 0;
                    if (updateSubscriptionLease.length()>0) {
                        updateSubscriptionLeaseTime = Integer.parseInt(updateSubscriptionLease);
                    }
                    Subscription updateSubscription = new Subscription(Integer.parseInt(updateSubscriptionId), updateSubscriptionSpecName, updateSubscriptionUri, updateSubscriptionExternRef, updateSubscriptionLeaseTime);
                    try {
                        System.out.println(api.updateSubscription(updateSubscription).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 98:    // b
                    System.out.println("Delete a subscription");
                    System.out.print("Enter an ID: ");
                    String deleteSubscriptionId = "";
                    try {
                        deleteSubscriptionId = inputBuffer.readLine();
                    } catch (IOException e) {
                        System.exit(0);
                    }
                    try {
                        api.deleteSubscription(Integer.parseInt(deleteSubscriptionId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
