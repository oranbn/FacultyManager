using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model
{
    public class ConnectionHandler
    {
        private NetworkStream netstr;
        private TcpClient client;
        public ConnectionHandler(String server, Int32 port)
        {
            Connect(server, port);
        }
        public void Connect(String server, Int32 port)
        {
            try
            {
                client = new TcpClient(server, port);
                netstr = client.GetStream();               
            }
            catch (ArgumentNullException e)
            {
                Console.WriteLine("ArgumentNullException: {0}", e);
            }
            catch (SocketException e)
            {
                Console.WriteLine("SocketException: {0}", e);
            }
        }
        public void Disconnect()
        {
            netstr.Close();
            client.Close();
        }
        public byte[] Receive()
        {
            try
            {
                // Buffer to store the response bytes.
                byte[] recv = new Byte[256];

                // Read the first batch of the TcpServer response bytes.
                int bytes = netstr.Read(recv, 0, recv.Length); //(**This receives the data using the byte method**)

                byte[] a = new byte[bytes];

                for (int i = 0; i < bytes; i++)
                {
                    a[i] = recv[i];
                }

                return a;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error!\n" + ex.Message + "\n" + ex.StackTrace);

                return null;
            }

        }

        public void Send(byte[] message)
        {
            try
            {
                //byte[] send = Encoding.UTF8.GetBytes(message.ToCharArray(), 0, message.Length);
                netstr.Write(message, 0, message.Length);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error!\n" + ex.Message + "\n" + ex.StackTrace);
            }
        }
    }
}
