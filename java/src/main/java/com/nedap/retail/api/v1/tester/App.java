package com.nedap.retail.api.v1.tester;

import com.nedap.retail.api.v1.model.*;
import com.nedap.retail.api.v1.tester.events.*;
import org.eclipse.jetty.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Store !D API tester for !D Top and !D Gate
 *
 */
public class App {

    private static final int TEST_API_PORTNR = 8088;
    private static final String RFID_TAG_ARRIVE = "rfid.tag.arrive";
    private static final String RFID_TAG_MOVE = "rfid.tag.move";

    private static final Set<String> EPC_COUNTER = new HashSet<>();

    public static void main(final String[] args) throws IOException {
        System.out.println("Store !D API tester for !D Top and !D Gate version 1.21 Java");

        if (args.length == 0) {
            System.out.println("Please use URL of device as parameter, for example: http://localhost:8081");
            System.out.println("Optionally, you can use the hostname of this computer as second parameter to start automatic testing.");
            System.out.println("Optionally, you can use the \"count\" as third parameter to count unique EPCs or \"log\" to log the events in CSV file format.");
            System.exit(0);
        }

        // instantiate API wrapper
        final ApiWrapper api = new ApiWrapper(args[0]);

        // if third argument is "count", start epc counting
        if (args.length == 3 && args[2].equals("count")) {
            countEpc(api, args[1]);
            System.exit(0);
        }

        // if third argument is "log", start epc logging
        if (args.length == 3 && args[2].equals("log")) {
            logEpc(api, args[1]);
            System.exit(0);
        }

        // if second argument is given, start automatic testing
        if (args.length == 2) {
            testApi(api, args[1]);
            System.exit(0);
        }

        final BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;
        while (running) {
            // show menu
            System.out.println();
            System.out.println("------------------------------------------------------");
            System.out.println("0. Test connection          1. Show status");
            System.out.println("c. Send action              g. Send heartbeat");
            System.out.println("d. Create spec, subscription and receive incoming events");
            System.out.println("e. Get settings             f. Update settings");
            System.out.println("h. Show number of unique EPCs and write them to file");
            System.out.println("-- SPECS --                 -- SUBSCRIPTIONS --");
            System.out.println("2. Show all specs           7. Show all subscriptions");
            System.out.println("3. Create new spec          8. Create new subscription");
            System.out.println("4. Show a spec              9. Show a subscription");
            System.out.println("5. Update a spec            a. Update a subscription");
            System.out.println("6. Delete a spec            b. Delete a subscription");
            System.out.println("Please enter your choice and press Enter, or just Enter to exit.");

            // get choice
            final String input = inputBuffer.readLine();

            // exit if no choice made
            if (input.length() == 0) {
                System.exit(0);
            }

            // print line
            System.out.println("------------------------------------------------------");

            // check what choice has been made
            switch (input.codePointAt(0)) {
                case '0':
                    System.out.println("Test connection");
                    api.testConnection();
                    break;
                case '1':
                    System.out.println("Show status");
                    System.out.println(api.getStatus().toString());
                    break;
                case '2':
                    System.out.println("Show specs");
                    System.out.println(api.getSpecs().toString());
                    break;
                case '3':
                    System.out.println("Create new spec");
                    System.out.print("Enter a name: ");
                    final String createSpecName = inputBuffer.readLine();

                    System.out.println("Which events?");
                    System.out.println("1 = " + RFID_TAG_ARRIVE);
                    System.out.println("2 = " + RFID_TAG_MOVE);
                    System.out.printf("3 = %s AND %s%n", RFID_TAG_ARRIVE, RFID_TAG_MOVE);
                    final String createSpecOptions = inputBuffer.readLine();

                    final String[] createSpecEvents;
                    if (createSpecOptions.equals("1")) {
                        createSpecEvents = new String[1];
                        createSpecEvents[0] = RFID_TAG_ARRIVE;
                    } else if (createSpecOptions.equals("2")) {
                        createSpecEvents = new String[1];
                        createSpecEvents[0] = RFID_TAG_MOVE;
                    } else {
                        createSpecEvents = new String[2];
                        createSpecEvents[0] = RFID_TAG_ARRIVE;
                        createSpecEvents[1] = RFID_TAG_MOVE;
                    }
                    final Spec createSpec = new Spec(0, createSpecName, createSpecEvents);
                    System.out.println(api.createSpec(createSpec).toString());
                    break;
                case '4':
                    System.out.println("Show a spec");
                    System.out.print("Enter an ID: ");
                    final String showSpecId = inputBuffer.readLine();
                    System.out.println(api.getSpec(Integer.parseInt(showSpecId)).toString());
                    break;
                case '5':
                    System.out.println("Update a spec");
                    System.out.print("Enter ID of spec to update: ");
                    final String updateSpecId = inputBuffer.readLine();

                    System.out.print("Enter a name: ");
                    final String updateSpecName = inputBuffer.readLine();

                    System.out.println("Which events?");
                    System.out.println("1 = " + RFID_TAG_ARRIVE);
                    System.out.println("2 = " + RFID_TAG_MOVE);
                    System.out.printf("3 = %s AND %s%n", RFID_TAG_ARRIVE, RFID_TAG_MOVE);
                    final String updateSpecOptions = inputBuffer.readLine();

                    final String[] updateSpecEvents;
                    if (updateSpecOptions.equals("1")) {
                        updateSpecEvents = new String[]{RFID_TAG_ARRIVE};
                    } else if (updateSpecOptions.equals("2")) {
                        updateSpecEvents = new String[]{RFID_TAG_MOVE};
                    } else {
                        updateSpecEvents = new String[]{RFID_TAG_ARRIVE, RFID_TAG_MOVE};
                    }

                    final Spec updateSpec = new Spec(
                            Integer.parseInt(updateSpecId), updateSpecName, updateSpecEvents
                    );
                    System.out.println(api.updateSpec(updateSpec).toString());
                    break;
                case '6':
                    System.out.println("Delete a spec");
                    System.out.print("Enter an ID: ");
                    final String deleteSpecId = inputBuffer.readLine();
                    api.deleteSpec(Integer.parseInt(deleteSpecId));
                    break;
                case '7':
                    System.out.println("Show subscriptions");
                    System.out.println(api.getSubscriptions().toString());
                    break;
                case '8':
                    System.out.println("Create new subscription");
                    System.out.print("Enter name of a spec: ");
                    final String createSubscriptionSpecName = inputBuffer.readLine();

                    System.out.print("URI to send events to: ");
                    final String createSubscriptionUri = inputBuffer.readLine();

                    System.out.print("External reference (optional): ");
                    final String createSubscriptionExternRef = inputBuffer.readLine();

                    System.out.print("Requested lease time in minutes: ");
                    final String createSubscriptionLease = inputBuffer.readLine();

                    final Integer createSubscriptionLeaseTime;
                    if (createSubscriptionLease.length() > 0) {
                        createSubscriptionLeaseTime = Integer.parseInt(createSubscriptionLease);
                    } else {
                        createSubscriptionLeaseTime = 0;
                    }
                    final Subscription createSubscription = new Subscription(
                            0, createSubscriptionSpecName, createSubscriptionUri, createSubscriptionExternRef,
                            createSubscriptionLeaseTime
                    );
                    System.out.println(api.createSubscription(createSubscription).toString());
                    break;
                case '9':
                    System.out.println("Show a subscription");
                    System.out.print("Enter an ID: ");
                    final String showSubscriptionId = inputBuffer.readLine();
                    System.out.println(api.getSubscription(Integer.parseInt(showSubscriptionId)).toString());
                    break;
                case 'a':
                    System.out.println("Update a subscription");
                    System.out.print("Enter ID of subscription to update: ");
                    final String updateSubscriptionId = inputBuffer.readLine();

                    System.out.print("Enter name of a spec: ");
                    final String updateSubscriptionSpecName = inputBuffer.readLine();

                    System.out.print("URI to send events to: ");
                    final String updateSubscriptionUri = inputBuffer.readLine();

                    System.out.print("External reference (optional): ");
                    final String updateSubscriptionExternRef = inputBuffer.readLine();

                    System.out.print("Requested lease time in minutes: ");
                    final String updateSubscriptionLease = inputBuffer.readLine();

                    final Integer updateSubscriptionLeaseTime;
                    if (updateSubscriptionLease.length() > 0) {
                        updateSubscriptionLeaseTime = Integer.parseInt(updateSubscriptionLease);
                    } else {
                        updateSubscriptionLeaseTime = 0;
                    }

                    final Subscription updateSubscription = new Subscription(
                            Integer.parseInt(updateSubscriptionId), updateSubscriptionSpecName,
                            updateSubscriptionUri, updateSubscriptionExternRef, updateSubscriptionLeaseTime
                    );

                    System.out.println(api.updateSubscription(updateSubscription).toString());
                    break;
                case 'b':
                    System.out.println("Delete a subscription");
                    System.out.print("Enter an ID: ");
                    final String deleteSubscriptionId = inputBuffer.readLine();
                    api.deleteSubscription(Integer.parseInt(deleteSubscriptionId));
                    break;
                case 'c':
                    System.out.println("Send an action");
                    System.out.println("Which action?");
                    System.out.println("1 = blink");
                    System.out.println("2 = beep");
                    System.out.println("3 = blink and beep");
                    final String sendActionOptions = inputBuffer.readLine();

                    System.out.print("How many times: ");
                    final int times = readInt(inputBuffer.readLine(), 3);

                    System.out.print("Time the lamp/buzzer is on (in milliseconds): ");
                    final int onTime = readInt(inputBuffer.readLine(), 300);

                    System.out.print("Time the lamp/buzzer is off (in milliseconds): ");
                    final int offTime = readInt(inputBuffer.readLine(), 600);

                    System.out.print("Time the lamp is on afterwards (in milliseconds): ");
                    final int holdTime = readInt(inputBuffer.readLine(), 3000);

                    final String sendActionAction;
                    if (sendActionOptions.equals("1")) {
                        sendActionAction = "blink";
                    } else if (sendActionOptions.equals("2")) {
                        sendActionAction = "beep";
                    } else if (sendActionOptions.equals("3")) {
                        sendActionAction = "blinkAndBeep";
                    } else {
                        sendActionAction = "";
                    }
                    final Action[] actions = new Action[1];
                    actions[0] = new Action(sendActionAction, times, onTime, offTime, holdTime);
                    api.sendActions(new Actions(actions));
                    break;
                case 'd':
                    System.out.print(
                            "On what hostname or IP address is this system reachable by the !D Top or !D Gate: "
                    );
                    final String testApiHostname = inputBuffer.readLine();
                    testApi(api, testApiHostname);
                    break;
                case 'e':
                    System.out.println("Show settings");
                    System.out.println(api.getSettings().toString());
                    break;
                case 'f':
                    System.out.println("Update settings");
                    Settings settings = new Settings();

                    System.out.print("Enable RFID reader (y for yes, n for no, anything else for no change): ");
                    final String updateSettingsEnableReader = inputBuffer.readLine();
                    if (updateSettingsEnableReader.equalsIgnoreCase("y")) {
                        settings = settings.withReaderEnabled(true);
                    } else if (updateSettingsEnableReader.equalsIgnoreCase("n")) {
                        settings = settings.withReaderEnabled(false);
                    }

                    System.out.print("Enable lights (y for yes, n for no, anything else for no change): ");
                    final String updateSettingsEnableLights = inputBuffer.readLine();
                    if (updateSettingsEnableLights.equalsIgnoreCase("y")) {
                        settings = settings.withLightsEnabled(true);
                    } else if (updateSettingsEnableLights.equalsIgnoreCase("n")) {
                        settings = settings.withLightsEnabled(false);
                    }

                    System.out.print("Enable buzzer (y for yes, n for no, anything else for no change): ");
                    final String updateSettingsEnableBuzzer = inputBuffer.readLine();
                    if (updateSettingsEnableBuzzer.equalsIgnoreCase("y")) {
                        settings = settings.withBuzzerEnabled(true);
                    } else if (updateSettingsEnableBuzzer.equalsIgnoreCase("n")) {
                        settings = settings.withBuzzerEnabled(false);
                    }

                    System.out.print("Buzzer volume (0-100, Enter for no change): ");
                    final int buzzerVolume = readInt(inputBuffer.readLine(), 50);
                    settings = settings.withBuzzerVolume(buzzerVolume);

                    api.updateSettings(settings);
                    break;
                case 'g':
                    System.out.println("Send heartbeat");
                    api.heartbeat();
                    break;
                case 'h':
                    System.out.print(
                            "On what hostname or IP address is this system reachable by the !D Top or !D Gate: "
                    );
                    final String countEpcHostname = inputBuffer.readLine();
                    countEpc(api, countEpcHostname);
                    System.exit(0);
                    break;
            }
        }
    }

    public static int readInt(final String input, final int defaultValue) {
        if (!input.matches("^[0-9]$")) {
            return defaultValue;
        } else {
            return Integer.parseInt(input);
        }
    }

    public static void testApi(final ApiWrapper api, final String ownHostname) throws IOException {
        try (
                final BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
                final EventsServer server = new EventsServer(
                        new RawRequestHandler(new BaseHandler()), new Server(TEST_API_PORTNR)
                )
        ) {

            System.out.println("Starting webserver...");
            new Thread(server).start();

            boolean systemOffline = true;
            while (systemOffline) {
                try {
                    api.heartbeat();
                    systemOffline = false;
                } catch (final Exception e) {
                    System.out.println("System is offline. Will keep trying...");
                }
            }

            final String[] testApiSpecEvents = new String[]{"rfid.tag.arrive", "rfid.tag.move"};

            System.out.println("Creating spec...");
            final Spec testApiSpec = api.createSpec(new Spec(0, "tester", testApiSpecEvents));

            System.out.println("Creating subscription...");
            final Subscription testApiSubscription = api.createSubscription(new Subscription(
                    0, "tester", "http://" + ownHostname + ":" + TEST_API_PORTNR + "/", "tester", 30
            ));

            // set timer to renew subscription every 29 minutes
            final RenewSubscriptionTask task = new RenewSubscriptionTask(api, testApiSubscription);
            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(task, 29L * 60L * 1000L, 29L * 60L * 1000L);

            System.out.println("Press x and Enter to exit");
            String key = "";
            while (!key.equals("x")) {
                key = inputBuffer.readLine();
            }

            System.out.println("Deleting spec and subscription");
            api.deleteSpec(testApiSpec.getId());
            api.deleteSubscription(testApiSubscription.getId());
            timer.cancel();
        }
    }

    public static void countEpc(final ApiWrapper api, final String ownHostname) throws IOException {
        try (
                final BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
                final EventsServer server = new EventsServer(
                        new EpcCountingRequestHandler(new BaseHandler(), EPC_COUNTER), new Server(TEST_API_PORTNR)
                )
        ) {

            System.out.print("Enter a description for the log file: ");
            final String description = inputBuffer.readLine();

            System.out.println("Starting webserver...");
            new Thread(server).start();

            final String[] testApiSpecEvents = new String[]{"rfid.tag.arrive"};

            System.out.println("Creating spec...");
            final Spec testApiSpec = api.createSpec(new Spec(0, "epccount", testApiSpecEvents));

            System.out.println("Creating subscription...");
            final Subscription testApiSubscription = api.createSubscription(new Subscription(
                    0, "epccount", "http://" + ownHostname + ":" + TEST_API_PORTNR + "/", "tester", 30
            ));

            // set timer to renew subscription every 29 minutes
            final RenewSubscriptionTask task = new RenewSubscriptionTask(api, testApiSubscription);
            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(task, 29L * 60L * 1000L, 29L * 60L * 1000L);

            System.out.println("Press x and Enter to exit");
            String key = "";
            while (!key.equals("x")) {
                key = inputBuffer.readLine();
            }

            System.out.println("Deleting spec and subscription");
            api.deleteSpec(testApiSpec.getId());
            api.deleteSubscription(testApiSubscription.getId());
            timer.cancel();

            LogFile.write(description);
            EPC_COUNTER.stream().map(Object::toString).forEach(LogFile::write);
            System.out.println("EPCs written to " + LogFile.FILENAME);
        }
    }

    public static void logEpc(final ApiWrapper api, final String ownHostname) throws IOException {
        try(
                final BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
                final EventsServer server = new EventsServer(
                        new EpcLoggingRequestHandler(new BaseHandler()), new Server(TEST_API_PORTNR)
                )
        ) {
            System.out.print("Enter a description for the log file: ");
            final String description = inputBuffer.readLine();
            LogFile.write(description);
            LogFile.write("\"eventId\";\"eventType\";\"occurTime\";\"direction\";\"EPC\";\"time\";\"easStatus\"");

            System.out.println("Writing EPCs to " + LogFile.FILENAME);
            System.out.println("Starting webserver...");
            new Thread(server).start();

            final String[] testApiSpecEvents = new String[]{ RFID_TAG_ARRIVE, RFID_TAG_MOVE};
            System.out.println("Creating spec...");
            final Spec testApiSpec = api.createSpec(new Spec(0, "epclog", testApiSpecEvents));

            System.out.println("Creating subscription...");
            final Subscription testApiSubscription = api.createSubscription(new Subscription(
                    0, "epclog", "http://" + ownHostname + ":" + TEST_API_PORTNR + "/", "tester", 30
            ));

            // set timer to renew subscription every 29 minutes
            final RenewSubscriptionTask task = new RenewSubscriptionTask(api, testApiSubscription);
            final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);
            scheduler.scheduleAtFixedRate(task, 29, 29, TimeUnit.MINUTES);

            System.out.println("Press x and Enter to exit");
            String key = "";
            while (!key.equals("x")) {
                key = inputBuffer.readLine();
            }

            System.out.println("Deleting spec and subscription");
            api.deleteSpec(testApiSpec.getId());
            api.deleteSubscription(testApiSubscription.getId());
            scheduler.shutdown();
        }
    }
}
