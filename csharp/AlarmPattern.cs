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

        public override String ToString()
        {
            StringBuilder result = new StringBuilder();
            result.Append("on time = ");
            result.Append(this.onTime);
            result.Append("\n");
            result.Append("off time = ");
            result.Append(this.offTime);
            result.Append("\n");
            result.Append("lights hold time = ");
            result.Append(this.lightsHoldTime);
            result.Append("\n");
            result.Append("count = ");
            result.Append(this.count);
            result.Append("\n");
            return result.ToString();
        }
    }
}
