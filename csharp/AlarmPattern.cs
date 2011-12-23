using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class AlarmPattern
    {
        [DataMember]
        public int onTime { get; set; }
        [DataMember]
        public int offTime { get; set; }
        [DataMember]
        public int lightsHoldTime { get; set; }
        [DataMember]
        public int count { get; set; }

        public AlarmPattern()
        {
        }

        public AlarmPattern(int onTime, int offTime, int lightsHoldTime, int count)
        {
            this.onTime = onTime;
            this.offTime = offTime;
            this.lightsHoldTime = lightsHoldTime;
            this.count = count;
        }
    }
}
