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
            Console.WriteLine("Store !D API tester for !D Top and !D Gate version 1.17 C#");

            if (args.Length == 0)
            {
                Console.WriteLine("Please use URL of device as parameter, for example: http://localhost:8081");
                Console.WriteLine("Optionally, you can use the hostname of this computer as second parameter to start automatic testing.");
                Environment.Exit(0);
            }

            // instantiate API wrapper
            ApiWrapper api = new ApiWrapper(args[0]);

            // if second argument is given, start automatic testing
            if (args.Length == 2)
            {
                testApi(api, args[1]);
                Environment.Exit(0);
            }

            Boolean running = true;
            while(running)
            {
                // show menu
                Console.WriteLine();
                Console.WriteLine("------------------------------------------------------");
                Console.WriteLine("0. Test connection          1. Show status");
                Console.WriteLine("c. Send action              g. send heartbeat");
                Console.WriteLine("d. Create spec, subscription and receive incoming events");
                Console.WriteLine("e. Get settings             f. Update settings");
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
                        Console.WriteLine("2 = rfid.tag.move");
                        Console.WriteLine("3 = rfid.tag.arrive AND rfid.tag.move");
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
                            createSpecEvents[0] = "rfid.tag.move";
                        } else {
                            createSpecEvents = new String[2];
                            createSpecEvents[0] = "rfid.tag.arrive";
                            createSpecEvents[1] = "rfid.tag.move";
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
                        Console.WriteLine("2 = rfid.tag.move");
                        Console.WriteLine("3 = rfid.tag.arrive AND rfid.tag.move");
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
                            updateSpecEvents[0] = "rfid.tag.move";
                        } else {
                            updateSpecEvents = new String[2];
                            updateSpecEvents[0] = "rfid.tag.arrive";
                            updateSpecEvents[1] = "rfid.tag.move";
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
                        Console.Write("Time the lamp/buzzer is on (in milliseconds): ");
                        String sendActionOnTime = "";
                        try {
                            sendActionOnTime = Console.ReadLine();
                        }
                        catch (Exception)
                        {
                            Environment.Exit(0);
                        }
                        Console.Write("Time the lamp/buzzer is off (in milliseconds): ");
                        String sendActionOffTime = "";
                        try {
                            sendActionOffTime = Console.ReadLine();
                        }
                        catch (Exception)
                        {
                            Environment.Exit(0);
                        }
                        Console.Write("Time the lamp is on afterwards (in milliseconds): ");
                        String sendActionHoldTime = "";
                        try {
                            sendActionHoldTime = Console.ReadLine();
                        }
                        catch (Exception)
                        {
                            Environment.Exit(0);
                        }
                        try
                        {
                            String sendActionAction = "";
                            if (sendActionOptions == "1")
                            {
                                sendActionAction = "blink";
                            }
                            else if (sendActionOptions == "2")
                            {
                                sendActionAction = "beep";
                            }
                            else if (sendActionOptions == "3")
                            {
                                sendActionAction = "blinkAndBeep";
                            }
                            Action[] actions = new Action[1];
                            actions[0] = new Action(sendActionAction, int.Parse(sendActionCount), int.Parse(sendActionOnTime), int.Parse(sendActionOffTime), int.Parse(sendActionHoldTime));
                            api.sendActions(new Actions(actions));
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.D:
                        Console.Write("On what hostname or IP address is this system reachable by the !D Top or !D Gate: ");
                        String testApiHostname = "";
                        try
                        {
                            testApiHostname = Console.ReadLine();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        testApi(api, testApiHostname);
                        break;
                    case ConsoleKey.E:
                        Console.WriteLine("Get settings");
                        try
                        {
                            Console.WriteLine(api.getSettings().ToString());
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.F:
                        Console.WriteLine("Update settings");
                        Settings settings = new Settings();
                        Console.Write("Enable RFID reader (y for yes, n for no, anything else for no change): ");
                        String updateSettingsEnableReader = "";
                        try
                        {
                            updateSettingsEnableReader = Console.ReadLine();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        if (updateSettingsEnableReader == "y")
                        {
                            settings.readerEnabled = true;
                        }
                        else if (updateSettingsEnableReader == "n")
                        {
                            settings.readerEnabled = false;
                        }
                        Console.Write("Enable lights (y for yes, n for no, anything else for no change): ");
                        String updateSettingsEnableLights = "";
                        try
                        {
                            updateSettingsEnableLights = Console.ReadLine();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        if (updateSettingsEnableLights == "y")
                        {
                            settings.lightsEnabled = true;
                        }
                        else if (updateSettingsEnableLights == "n")
                        {
                            settings.lightsEnabled = false;
                        }
                        Console.Write("Enable buzzer (y for yes, n for no, anything else for no change): ");
                        String updateSettingsEnableBuzzer = "";
                        try
                        {
                            updateSettingsEnableBuzzer = Console.ReadLine();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        if (updateSettingsEnableBuzzer == "y")
                        {
                            settings.buzzerEnabled = true;
                        }
                        else if (updateSettingsEnableBuzzer == "n")
                        {
                            settings.buzzerEnabled = false;
                        }
                        Console.Write("Buzzer volume (0-100, Enter for no change): ");
                        String updateSettingsBuzzerVolume = "";
                        try
                        {
                            updateSettingsBuzzerVolume = Console.ReadLine();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        if (updateSettingsBuzzerVolume != "")
                        {
                            settings.buzzerVolume = int.Parse(updateSettingsBuzzerVolume);
                        }
                        Console.Write("Set a new alarm pattern? (y for yes, n for no): ");
                        String updateSettingsAlarmPattern = "";
                        try
                        {
                            updateSettingsAlarmPattern = Console.ReadLine();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        if (updateSettingsAlarmPattern == "y")
                        {
                            AlarmPattern alarmPattern = new AlarmPattern();
                            Console.Write("Time lights/buzzer are on (in milliseconds, default=400): ");
                            String updateSettingsInput = "";
                            try
                            {
                                updateSettingsInput = Console.ReadLine();
                            }
                            catch (Exception e)
                            {
                                Console.WriteLine(e.ToString());
                            }
                            alarmPattern.onTime = int.Parse(updateSettingsInput);
                            Console.Write("Time lights/buzzer are off (in milliseconds, default=50): ");
                            try
                            {
                                updateSettingsInput = Console.ReadLine();
                            }
                            catch (Exception e)
                            {
                                Console.WriteLine(e.ToString());
                            }
                            alarmPattern.offTime = int.Parse(updateSettingsInput);
                            Console.Write("Time lights are on after last cycle (in milliseconds, default=7000): ");
                            try
                            {
                                updateSettingsInput = Console.ReadLine();
                            }
                            catch (Exception e)
                            {
                                Console.WriteLine(e.ToString());
                            }
                            alarmPattern.lightsHoldTime = int.Parse(updateSettingsInput);
                            Console.Write("Number of cycles (default=5): ");
                            try
                            {
                                updateSettingsInput = Console.ReadLine();
                            }
                            catch (Exception e)
                            {
                                Console.WriteLine(e.ToString());
                            }
                            alarmPattern.count = int.Parse(updateSettingsInput);
                            settings.alarmPattern = alarmPattern;
                        }
                        try
                        {
                            api.updateSettings(settings);
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                    case ConsoleKey.G:
                        Console.WriteLine("Send heartbeat");
                        try
                        {
                            api.heartbeat();
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.ToString());
                        }
                        break;
                }
            }
        }

        public static void testApi(ApiWrapper api, String ownHostname)
        {

            int testApiPortnr = 8088;
            Console.WriteLine("Starting webserver...");

            EventsServer server = new EventsServer(testApiPortnr);

            String[] testApiSpecEvents = new String[2];
            testApiSpecEvents[0] = "rfid.tag.arrive";
            testApiSpecEvents[1] = "rfid.tag.move";
            Console.WriteLine("Creating spec...");
            Spec testApiSpec = new Spec(0, "tester", testApiSpecEvents);
            try
            {
                testApiSpec = api.createSpec(testApiSpec);
            }
            catch (Exception)
            {
            }
            Console.WriteLine("Creating subscription...");
            Subscription testApiSubscription = new Subscription(0, "tester", "http://" + ownHostname + ":" + testApiPortnr + "/", "tester", 30);
            try
            {
                testApiSubscription = api.createSubscription(testApiSubscription);
            }
            catch (Exception)
            {
            }
            // set timer to renew subscription every 29 minutes
            RenewSubscriptionTimer renewSubscriptionTimer = new RenewSubscriptionTimer(api, testApiSubscription);
            // start server
            server.Start();

            Console.WriteLine("Press x and Enter to exit");
            String key = "";
            while (key != "x")
            {
                try
                {
                    key = Console.ReadLine();
                }
                catch (Exception)
                {
                }
            }

            // cleaning up
            server.Stop();
            
            Console.WriteLine("Deleting spec and subscription");
            api.deleteSpec(testApiSpec.id);
            api.deleteSubscription(testApiSubscription.id);
            renewSubscriptionTimer.stop();
        }
    }
}
