using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.IO;
using System.Runtime.Serialization.Json;

namespace StoreIDTopGateAPI
{
    class ApiWrapper
    {
        private String baseUrl;

        /**
         * Constructor
         * @param url base URL for all API calls
         */
        public ApiWrapper(String baseUrl)
        {
            // make sure baseUrl does not end with a slash
            if (baseUrl.EndsWith("/"))
            {
                this.baseUrl = baseUrl.Substring(0, baseUrl.Length - 1);
            }
            else
            {
                this.baseUrl = baseUrl;
            }
        }

        public Status getStatus()
        {
            String httpResult = doHttpRequest("/status");
            return (Status)fromJson(httpResult, typeof(Status));
        }

        public SpecList getSpecs()
        {
            String httpResult = doHttpRequest("/service/events/specs");
            return (SpecList)fromJson(httpResult, typeof(SpecList));
        }
        
        public Spec createSpec(Spec spec)
        {
            String httpResult = doHttpRequest("/service/events/specs", "POST", toJson(spec, typeof(Spec)));
            return (Spec)fromJson(httpResult, typeof(Spec));
        }
        
        public Spec getSpec(int id)
        {
            String httpResult = doHttpRequest("/service/events/specs/" + id);
            return (Spec)fromJson(httpResult, typeof(Spec));
        }
        
        public void deleteSpec(int id)
        {
            String httpResult = doHttpRequest("/service/events/specs/" + id, "DELETE");
        }
        
        public Spec updateSpec(Spec spec)
        {
            String httpResult = doHttpRequest("/service/events/specs/" + spec.id, "PUT", toJson(spec, typeof(Spec)));
            return (Spec)fromJson(httpResult, typeof(Spec));
        }
    
        public SubscriptionList getSubscriptions()
        {
            String httpResult = doHttpRequest("/service/events/subscriptions");
            return (SubscriptionList)fromJson(httpResult, typeof(SubscriptionList));
        }
        
        public Subscription createSubscription(Subscription subscription)
        {
            String httpResult = doHttpRequest("/service/events/subscriptions", "POST", toJson(subscription, typeof(Subscription)));
            return (Subscription)fromJson(httpResult, typeof(Subscription));
        }
        
        public Subscription getSubscription(int id)
        {
            String httpResult = doHttpRequest("/service/events/subscriptions/" + id);
            return (Subscription)fromJson(httpResult, typeof(Subscription));
        }
        
        public void deleteSubscription(int id)
        {
            String httpResult = doHttpRequest("/service/events/subscriptions/" + id, "DELETE");
        }
        
        public Subscription updateSubscription(Subscription subscription) {
            String httpResult = doHttpRequest("/service/events/subscriptions/" + subscription.id, "PUT", toJson(subscription, typeof(Subscription)));
            return (Subscription)fromJson(httpResult, typeof(Subscription));
        }

        public void sendActions(Actions actions)
        {
            String httpResult = doHttpRequest("/service/actions", "POST", toJson(actions, typeof(Actions)));
        }

        public Settings getSettings()
        {
            String httpResult = doHttpRequest("/service/settings", "GET");
            return (Settings)fromJson(httpResult, typeof(Settings));
        }

        public void updateSettings(Settings settings)
        {
            String httpResult = doHttpRequest("/service/settings", "PUT", toJson(settings, typeof(Settings)));
        }

        public void heartbeat()
        {
            String httpResult = doHttpRequest("/heartbeat", "GET");
        }

        public void testConnection() {
            try {
                String httpResult = doHttpRequest("/status");
                if (httpResult.Length>4)
                {
                    Console.WriteLine("Connection OK");
                }
                else
                {
                    Console.WriteLine("Invalid response received");
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
        }

        /**
         * Helper function for HTTP requests
         */
        private String doHttpRequest(String url, String requestMethod = "GET", String data = "")
        {
            byte[] dataBytes = System.Text.Encoding.UTF8.GetBytes(data);
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(this.baseUrl + url);
            if (requestMethod == "GET")
            {
                // is the default, do nothing
            }
            else if ((requestMethod == "POST") || (requestMethod == "PUT"))
            {
                request.Method = requestMethod;
                request.ContentType = "application/json";
                request.ContentLength = dataBytes.Length;
                Stream dataStream = request.GetRequestStream();
                dataStream.Write(dataBytes, 0, dataBytes.Length);
                dataStream.Close();
            }
            else if (requestMethod == "DELETE")
            {
                request.Method = requestMethod;
            }
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            Console.WriteLine("Response code = " + response.StatusCode);
            StreamReader streamReader = new StreamReader(response.GetResponseStream());
            String result = "";

            while(streamReader.Peek() != -1)
            {
                result += streamReader.ReadLine();
            }
            return result;
        }

        /**
         * Helper function for JSON serialization
         * @see http://msdn.microsoft.com/en-us/library/bb412179.aspx
         */
        private Object fromJson(String json, Type theClass)
        {
            DataContractJsonSerializer ser = new DataContractJsonSerializer(theClass);
            byte[] dataBytes = System.Text.Encoding.UTF8.GetBytes(json);
            MemoryStream stream = new MemoryStream(dataBytes);
            return ser.ReadObject(stream);
        }

        /**
         * Helper function for JSON serialization
         * @see http://msdn.microsoft.com/en-us/library/bb412179.aspx
         */
        private String toJson(Object obj, Type theClass)
        {
            DataContractJsonSerializer ser = new DataContractJsonSerializer(theClass);
            MemoryStream stream = new MemoryStream();
            ser.WriteObject(stream, obj);
            stream.Position = 0;
            StreamReader streamreader = new StreamReader(stream);
            return streamreader.ReadToEnd();
        }
    }
}
