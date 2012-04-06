using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class Device
    {
        [DataMember]
        public String serial { get; set; }
        [DataMember]
        public String hardwareVersion { get; set; }
        [DataMember]
        public String softwareVersion { get; set; }
        [DataMember]
        public String description { get; set; }
        [DataMember]
        public String modelDescription { get; set; }
        [DataMember]
        public String modelName { get; set; }
        [DataMember]
        public String modelNumber { get; set; }
        [DataMember]
        public String modelURL { get; set; }
        [DataMember]
        public String operationalStatus { get; set; }
        [DataMember]
        public String operationalMessage { get; set; }
        [DataMember]
        public String role { get; set; }

        public override String ToString() {
            StringBuilder result = new StringBuilder();
            result.Append("serial = ");
            result.Append(this.serial);
            result.Append("\n");
            result.Append("hardware version = ");
            result.Append(this.hardwareVersion);
            result.Append("\n");
            result.Append("firmware version = ");
            result.Append(this.softwareVersion);
            result.Append("\n");
            result.Append("description = ");
            result.Append(this.description);
            result.Append("\n");
            result.Append("model description = ");
            result.Append(this.modelDescription);
            result.Append("\n");
            result.Append("model name = ");
            result.Append(this.modelName);
            result.Append("\n");
            result.Append("model number = ");
            result.Append(this.modelNumber);
            result.Append("\n");
            result.Append("model URL = ");
            result.Append(this.modelURL);
            result.Append("\n");
            result.Append("operational status = ");
            result.Append(this.operationalStatus);
            result.Append("\n");
            result.Append("operational message = ");
            result.Append(this.operationalMessage);
            result.Append("\n");
            result.Append("role = ");
            result.Append(this.role);
            return result.ToString();
        }    
    }
}
