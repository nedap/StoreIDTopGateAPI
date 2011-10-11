using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class SubscriptionList
    {
        [DataMember]
        public List<Subscription> subscriptions { get; set; }

        public override String ToString() {
            StringBuilder result = new StringBuilder();
            result.Append(subscriptions.Count);
            result.Append(" subscription(s):\n");
            foreach (Subscription s in subscriptions) {
                result.Append(s.ToString());
                result.Append("\n");
            }
            return result.ToString();
        }
    }
}
