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
        public int? buzzerVolume { get; set; }
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

        public override String ToString()
        {
            StringBuilder result = new StringBuilder();
            result.Append("reader enabled = ");
            result.Append(this.readerEnabled);
            result.Append("\n");
            result.Append("lights enabled = ");
            result.Append(this.lightsEnabled);
            result.Append("\n");
            result.Append("buzzer enabled = ");
            result.Append(this.buzzerEnabled);
            result.Append("\n");
            result.Append("buzzer volume = ");
            result.Append(this.buzzerVolume);
            result.Append("\n");
            result.Append("alarm pattern:");
            result.Append("\n");
            result.Append(this.alarmPattern.ToString());
            result.Append("\n");
            return result.ToString();
        }
    }
}
