using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [Obsolete]
    [DataContract]
    class Reader
    {
        [DataMember]
        public String hardwareVersion { get; set; }
        [DataMember]
        public String softwareVersion { get; set; }
        [DataMember]
        public String model { get; set; }
        [DataMember]
        public String connectedAntenna { get; set; }
        [DataMember]
        public String temperature { get; set; }
    }
}
