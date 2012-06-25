using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;

namespace StoreIDTopGateAPI
{
    class EventsServer
    {
        protected HttpListener listener = new HttpListener();
        protected int port;

        public EventsServer(int port)
        {
            this.port = port;
            if (!HttpListener.IsSupported)
            {
                Console.WriteLine("Windows XP SP2 or Server 2003 is required to use the HttpListener class.");
                return;
            }
            listener.Prefixes.Add("http://+:" + port.ToString() + "/");
        }

        public void Start()
        {
            try
            {
                listener.Start();
            }
            catch (HttpListenerException)
            {
                // If you get an Access Denied exception in Windows 7 or Windows 2008 or later, you might need to reserve a namespace. Run a console window as Admin, and type something like
                // netsh http add urlacl http://+:8088/ user=domain\user
                // http://msdn.microsoft.com/en-us/library/system.net.httplistener.start.aspx
                Console.WriteLine("Error starting webserver. If you are running Windows 7 or Windows 2008 or later, you might need to reserve a namespace.");
                Console.WriteLine("Run a console window as Admin, and type something like:");
                Console.WriteLine("netsh http add urlacl http://+:8088/ user=domain\\user");
                Environment.Exit(1);
            }
            listener.BeginGetContext(new AsyncCallback(ListenerCallback), null);
        }

        public void ListenerCallback(IAsyncResult result)
        {
            HttpListenerContext context;
            try
            {
                context = listener.EndGetContext(result);
            }
            catch (HttpListenerException)
            {
                return;
            }
            HttpListenerRequest request = context.Request;
            HttpListenerResponse response = context.Response;

            System.IO.StreamReader r = new System.IO.StreamReader(request.InputStream);
            Console.WriteLine(r.ReadToEnd());
            string responseString = "Ok";
            byte[] buffer = System.Text.Encoding.UTF8.GetBytes(responseString);
            response.ContentLength64 = buffer.Length;
            System.IO.Stream output = response.OutputStream;
            output.Write(buffer, 0, buffer.Length);
            output.Close();
            listener.BeginGetContext(new AsyncCallback(ListenerCallback), null);
        }

        public void Stop()
        {
            listener.Stop();
        }
    }
}
