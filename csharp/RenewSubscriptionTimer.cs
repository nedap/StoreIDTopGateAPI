using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Timers;

namespace StoreIDTopGateAPI
{
    class RenewSubscriptionTimer
    {
        private static ApiWrapper api;
        private static Subscription subscription;
        private Timer timer;

        public RenewSubscriptionTimer(ApiWrapper _api, Subscription _subscription)
        {
            api = _api;
            subscription = _subscription;

            timer = new Timer(29 * 60 * 1000);
            timer.Elapsed += new ElapsedEventHandler(OnTimedEvent);
            timer.Enabled = true;
        }

        private static void OnTimedEvent(object source, ElapsedEventArgs e)
        {
            api.updateSubscription(subscription);
        }

        public void stop()
        {
            timer.Enabled = false;
        }
    }
}
