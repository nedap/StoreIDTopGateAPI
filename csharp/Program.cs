using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StoreIDTopGateAPI
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Store !D API tester for !D Top and !D Gate");

            if (args.Length == 0)
            {
                Console.WriteLine("Please use URL of device as parameter, for example: http://localhost:8081");
                Environment.Exit(0);
            }

            // instantiate API wrapper
            ApiWrapper api = new ApiWrapper(args[0]);

            Boolean running = true;
            while(running)
            {
                // show menu
                Console.WriteLine();
                Console.WriteLine("------------------------------------------------------");
                Console.WriteLine("0. Test connection");
                Console.WriteLine("1. Show status");
                Console.WriteLine("c. Send action");
                Console.WriteLine("-- SPECS --                 -- SUBSCRIPTIONS --");
                Console.WriteLine("2. Show all specs           7. Show all subscriptions");
                Console.WriteLine("3. Create new spec          8. Create new subscription");
                Console.WriteLine("4. Show a spec              9. Show a subscription");
                Console.WriteLine("5. Update a spec            a. Update a subscription");
                Console.WriteLine("6. Delete a spec            b. Delete a subscription");
                Console.WriteLine("Please enter your choice, or Enter to exit.");

                // get choice
                ConsoleKeyInfo key;
                key = Console.ReadKey();
                
                // exit if no choice made
                if (key.Key == ConsoleKey.Enter)
                {
                    Environment.Exit(0);
                }
                
                // print line
                Console.WriteLine("");
                Console.WriteLine("------------------------------------------------------");

                switch (key.Key)
                {
                    case ConsoleKey.D0: // 0
                        Console.WriteLine("Test Connection");
                        try
                        {
                            api.testConnection();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D1: // 1
                        Console.WriteLine("Show status");
                        try
                        {
                            Console.WriteLine(api.getStatus().ToString());
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D2:    // 2
                        Console.WriteLine("Show specs");
                        try {
                            Console.WriteLine(api.getSpecs().ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D3:    // 3
                        Console.WriteLine("Create new spec");
                        Console.Write("Enter a name: ");
                        String createSpecName = "Test";
                        try {
                            createSpecName = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.WriteLine("Which events?");
                        Console.WriteLine("1 = rfid.tag.arrive");
                        Console.WriteLine("2 = rfid.tag.depart");
                        Console.WriteLine("3 = rfid.tag.arrive AND rfid.tag.depart");
                        String createSpecOptions = "3";
                        try {
                            createSpecOptions = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        String[] createSpecEvents;
                        if (createSpecOptions=="1") {
                            createSpecEvents = new String[1];
                            createSpecEvents[0] = "rfid.tag.arrive";
                        } else if (createSpecOptions=="2") {
                            createSpecEvents = new String[1];
                            createSpecEvents[0] = "rfid.tag.depart";
                        } else {
                            createSpecEvents = new String[2];
                            createSpecEvents[0] = "rfid.tag.arrive";
                            createSpecEvents[1] = "rfid.tag.depart";
                        }
                        Spec createSpec = new Spec(0, createSpecName, createSpecEvents);
                        try {
                            Console.WriteLine(api.createSpec(createSpec).ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D4:    // 4
                        Console.WriteLine("Show a spec");
                        Console.Write("Enter an ID: ");
                        String showSpecId = "";
                        try {
                            showSpecId = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        try {
                            Console.WriteLine(api.getSpec(int.Parse(showSpecId)).ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D5:    // 5
                        Console.WriteLine("Update a spec");
                        Console.Write("Enter ID of spec to update: ");
                        String updateSpecId = "";
                        try {
                            updateSpecId = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("Enter a name: ");
                        String updateSpecName = "Test";
                        try {
                            updateSpecName = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.WriteLine("Which events?");
                        Console.WriteLine("1 = rfid.tag.arrive");
                        Console.WriteLine("2 = rfid.tag.depart");
                        Console.WriteLine("3 = rfid.tag.arrive AND rfid.tag.depart");
                        String updateSpecOptions = "3";
                        try {
                            updateSpecOptions = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        String[] updateSpecEvents;
                        if (updateSpecOptions=="1") {
                            updateSpecEvents = new String[1];
                            updateSpecEvents[0] = "rfid.tag.arrive";
                        } else if (updateSpecOptions=="2") {
                            updateSpecEvents = new String[1];
                            updateSpecEvents[0] = "rfid.tag.depart";
                        } else {
                            updateSpecEvents = new String[2];
                            updateSpecEvents[0] = "rfid.tag.arrive";
                            updateSpecEvents[1] = "rfid.tag.depart";
                        }
                        Spec updateSpec = new Spec(int.Parse(updateSpecId), updateSpecName, updateSpecEvents);
                        try {
                            Console.WriteLine(api.updateSpec(updateSpec).ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D6:    // 6
                        Console.WriteLine("Delete a spec");
                        Console.Write("Enter an ID: ");
                        String deleteSpecId = "";
                        try {
                            deleteSpecId = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        try {
                            api.deleteSpec(int.Parse(deleteSpecId));
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D7:    // 7
                        Console.WriteLine("Show subscriptions");
                        try {
                            Console.WriteLine(api.getSubscriptions().ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D8:    // 8
                        Console.WriteLine("Create new subscription");
                        Console.Write("Enter name of a spec: ");
                        String createSubscriptionSpecName = "";
                        try {
                            createSubscriptionSpecName = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("URI to send events to: ");
                        String createSubscriptionUri = "";
                        try {
                            createSubscriptionUri = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("External reference (optional): ");
                        String createSubscriptionExternRef = "";
                        try {
                            createSubscriptionExternRef = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("Requested lease time in minutes: ");
                        String createSubscriptionLease = "";
                        try {
                            createSubscriptionLease = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        int createSubscriptionLeaseTime = 0;
                        if (createSubscriptionLease.Length>0) {
                            createSubscriptionLeaseTime = int.Parse(createSubscriptionLease);
                        }
                        Subscription createSubscription = new Subscription(0, createSubscriptionSpecName, createSubscriptionUri, createSubscriptionExternRef, createSubscriptionLeaseTime);
                        try {
                            Console.WriteLine(api.createSubscription(createSubscription).ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D9:    // 9
                        Console.WriteLine("Show a subscription");
                        Console.Write("Enter an ID: ");
                        String showSubscriptionId = "";
                        try {
                            showSubscriptionId = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        try {
                            Console.WriteLine(api.getSubscription(int.Parse(showSubscriptionId)).ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.A:    // a
                        Console.WriteLine("Update a subscription");
                        Console.Write("Enter ID of subscription to update: ");
                        String updateSubscriptionId = "";
                        try {
                            updateSubscriptionId = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("Enter name of a spec: ");
                        String updateSubscriptionSpecName = "";
                        try {
                            updateSubscriptionSpecName = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("URI to send events to: ");
                        String updateSubscriptionUri = "";
                        try {
                            updateSubscriptionUri = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("External reference (optional): ");
                        String updateSubscriptionExternRef = "";
                        try {
                            updateSubscriptionExternRef = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("Requested lease time in minutes: ");
                        String updateSubscriptionLease = "";
                        try {
                            updateSubscriptionLease = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        int updateSubscriptionLeaseTime = 0;
                        if (updateSubscriptionLease.Length>0) {
                            updateSubscriptionLeaseTime = int.Parse(updateSubscriptionLease);
                        }
                        Subscription updateSubscription = new Subscription(int.Parse(updateSubscriptionId), updateSubscriptionSpecName, updateSubscriptionUri, updateSubscriptionExternRef, updateSubscriptionLeaseTime);
                        try {
                            Console.WriteLine(api.updateSubscription(updateSubscription).ToString());
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.B:    // b
                        Console.WriteLine("Delete a subscription");
                        Console.Write("Enter an ID: ");
                        String deleteSubscriptionId = "";
                        try {
                            deleteSubscriptionId = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        try {
                            api.deleteSubscription(int.Parse(deleteSubscriptionId));
                        } catch (Exception e) {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.C:    // c
                        Console.WriteLine("Send an action");
                        Console.WriteLine("Which action?");
                        Console.WriteLine("1 = blink");
                        Console.WriteLine("2 = beep");
                        Console.WriteLine("3 = blink and beep");
                        String sendActionOptions = "3";
                        try {
                            sendActionOptions = Console.ReadLine();
                        } catch (Exception) {
                            Environment.Exit(0);
                        }
                        Console.Write("How many times: ");
                        String sendActionCount = "";
                        try
                        {
                            sendActionCount = Console.ReadLine();
                        }
                        catch (Exception)
                        {
                            Environment.Exit(0);
                        }
                        try
                        {
                            Action[] actions = new Action[0];
                            if (sendActionOptions == "1")
                            {
                                actions = new Action[1];
                                actions[0] = new Action("blink", int.Parse(sendActionCount));
                            }
                            else if (sendActionOptions == "2")
                            {
                                actions = new Action[1];
                                actions[0] = new Action("beep", int.Parse(sendActionCount));
                            }
                            else if (sendActionOptions == "3")
                            {
                                actions = new Action[2];
                                actions[0] = new Action("blink", int.Parse(sendActionCount));
                                actions[1] = new Action("beep", int.Parse(sendActionCount));
                            }
                            api.sendAction(actions);
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                }
            }
        }
    }
}
