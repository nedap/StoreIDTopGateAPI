using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.Net;
using System.Threading;

namespace StoreIDTopGateAPI
{
    class EventsServer
    {
        protected int port;
        protected IPAddress ip;
        TcpListener listener;
        bool is_active = true;
        Thread myThread;

        public EventsServer(IPAddress ip, int port)
        {
            this.ip = ip;
            this.port = port;
        }

        public void listen()
        {
            listener = new TcpListener(ip, port);
            listener.Start();
            while (is_active)
            {
                TcpClient client = listener.AcceptTcpClient();
                EventsServerConnectionHandler handler = new EventsServerConnectionHandler(client, this);
                Thread thread = new Thread(new ThreadStart(handler.handle));
                thread.Start();
            }


        }

        public void start()
        {
            myThread = new Thread(new ThreadStart(this.listen));
            myThread.Start();
        }

        public void stop()
        {
            myThread.Abort();
        }
    }
}
