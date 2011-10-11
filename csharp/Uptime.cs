using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [Obsolete]
    [DataContract]
    class Uptime
    {
        [DataMember]
        public int? hours { get; set; }
        [DataMember]
        public int? minutes { get; set; }
        [DataMember]
        public int? seconds { get; set; }
        [DataMember]
        public int? year { get; set; }
        [DataMember]
        public int? month { get; set; }
        [DataMember]
        public int? date { get; set; }
        [DataMember]
        public int? day { get; set; }
        [DataMember]
        public int? time { get; set; }
        [DataMember]
        public int? timezoneOffset { get; set; }
    }
}
