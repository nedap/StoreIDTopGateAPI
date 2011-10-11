using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class Subscription
    {
        [DataMember]
        public int id { get; set; }
        [DataMember]
        public String specname { get; set; }
        [DataMember]
        public String notification_uri { get; set; }
        [DataMember]
        public String extern_ref { get; set; }
        [DataMember]
        public int lease { get; set; }

        public Subscription(int id, String specname, String notification_uri, String extern_ref, int lease) {
            this.id = id;
            this.specname = specname;
            this.notification_uri = notification_uri;
            this.extern_ref = extern_ref;
            this.lease = lease;
        }

        public override String ToString() {
            StringBuilder result = new StringBuilder();
            result.Append("id = ");
            result.Append(this.id);
            result.Append("\n");
            result.Append("specname = ");
            result.Append(this.specname);
            result.Append("\n");
            result.Append("notification uri = ");
            result.Append(this.notification_uri);
            result.Append("\n");
            result.Append("external reference = ");
            result.Append(this.extern_ref);
            result.Append("\n");
            result.Append("lease time = ");
            result.Append(this.lease);
            result.Append(" minutes\n");
            return result.ToString();
        }
    }
}
