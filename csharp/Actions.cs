using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class Actions
    {
        [DataMember]
        public Action[] actions { get; set; }

        public Actions(Action[] actions) {
            this.actions = actions;
        }

        public Actions(int count) {
            this.actions = new Action[count];
        }

        public Action[] getActions() {
            return this.actions;
        }

        public void add(int index, Action action) {
            this.actions[index] = action;
        }
    }
}
