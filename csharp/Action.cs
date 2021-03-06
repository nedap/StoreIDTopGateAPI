﻿using System;
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
        [DataMember]
        public int onTime { get; set; }
        [DataMember]
        public int offTime { get; set; }
        [DataMember]
        public int lightsHoldTime { get; set; }

        public Action(String action, int count, int onTime, int offTime, int lightsHoldTime) {
            this.action = action;
            this.count = count;
            this.onTime = onTime;
            this.offTime = offTime;
            this.lightsHoldTime = lightsHoldTime;
        }

    }
}
