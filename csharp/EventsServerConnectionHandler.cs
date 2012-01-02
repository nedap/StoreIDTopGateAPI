using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.IO;
using System.Threading;

namespace StoreIDTopGateAPI
{
    class EventsServerConnectionHandler
    {
        private TcpClient client;
        private EventsServer server;
        private Stream inputStream;
        private StreamWriter outputStream;

        public EventsServerConnectionHandler(TcpClient client, EventsServer server)
        {
            this.client = client;
            this.server = server;
        }

        public void handle()
        {
            inputStream = new BufferedStream(client.GetStream());
            outputStream = new StreamWriter(new BufferedStream(client.GetStream()));
            String message = "";
            Boolean readingHeader = true;
            Boolean reading = true;
            // skip headers
            while (reading && ((message = streamReadLine()) != null))
            {
                if (readingHeader)
                {
                    if (message.Equals(""))
                    {
                        readingHeader = false;
                    }
                }
                else
                {
                    if (message.Equals("0"))
                    {
                        // chunk length 0 means end of request
                        reading = false;
                    }
                    else
                    {
                        Console.WriteLine(message);
                    }
                }
            }

            // send reply
            outputStream.Write("HTTP/1.0 200 OK\n");
            outputStream.Write("Content-Type: text/html\n");
            outputStream.Write("Connection: close\n");
            outputStream.Write("Content-Length: 3\n");
            outputStream.Write("\n");
            outputStream.Write("Ok\n");
            outputStream.Flush();
            outputStream.Close();
            inputStream.Close();
            client.Close();
        }

        private string streamReadLine() {
            int next_char;
            string data = "";
            while (true) {
                next_char = inputStream.ReadByte();
                if (next_char == '\n') { break; }
                if (next_char == '\r') { continue; }
                if (next_char == -1) { Thread.Sleep(1); continue; };
                data += Convert.ToChar(next_char);
            }            
           return data;
       }
    }
}
