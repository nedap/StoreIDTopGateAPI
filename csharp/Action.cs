using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class Action
    {
        [DataMember]
        public String action { get; set; }
        [DataMember]
        public int count { get; set; }

        public Action(String action, int count) {
            this.action = action;
            this.count = count;
        }

    }
}
