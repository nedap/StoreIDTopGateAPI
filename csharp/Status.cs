using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class Status
    {
        [DataMember]
        public Device device { get; set; }

        public override String ToString()
        {
            return device.ToString();
        }
    }
}
