using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class Spec
    {
        [DataMember]
        public int id { get; set; }
        [DataMember]
        public String name { get; set; }
        [DataMember]
        public String[] event_types { get; set; }

        public Spec(int id, String name, String[] event_types) {
            this.id = id;
            this.name = name;
            this.event_types = event_types;
        }
    
        public override String ToString() {
            StringBuilder result = new StringBuilder();
            result.Append("id = ");
            result.Append(this.id);
            result.Append("\n");
            result.Append("name = ");
            result.Append(this.name);
            result.Append("\n");
            result.Append("event types = ");
            if (event_types!=null) {
                foreach(String e in event_types) {
                    result.Append(e);
                    result.Append(" ");
                }
            }
            return result.ToString();
        }
    }
}
