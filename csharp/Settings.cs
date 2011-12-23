using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace StoreIDTopGateAPI
{
    [DataContract]
    class Settings
    {
        [DataMember]
        public Boolean readerEnabled { get; set; }
        [DataMember]
        public Boolean lightsEnabled { get; set; }
        [DataMember]
        public Boolean buzzerEnabled { get; set; }
        [DataMember]
        public int buzzerVolume { get; set; }
        [DataMember]
        public AlarmPattern alarmPattern { get; set; }

        public Settings()
        {
        }

        public Settings(Boolean readerEnabled, Boolean lightsEnabled, Boolean buzzerEnabled, int buzzerVolume, AlarmPattern alarmPattern)
        {
            this.readerEnabled = readerEnabled;
            this.lightsEnabled = lightsEnabled;
            this.buzzerEnabled = buzzerEnabled;
            this.buzzerVolume = buzzerVolume;
            this.alarmPattern = alarmPattern;
        }
    }
}
