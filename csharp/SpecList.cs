using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class SpecList
    {
        [DataMember]
        public List<Spec> specs { get; set; }

        public override String ToString() {
            StringBuilder result = new StringBuilder();
            result.Append(specs.Count);
            result.Append(" spec(s):\n");
            foreach (Spec s in specs) {
                result.Append(s.ToString());
                result.Append("\n");
            }
            return result.ToString();
        }
    }
}
