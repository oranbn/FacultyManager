using System;
using System.Collections.Generic;
using System.Threading;
using FacultyManager.Model.Operations;
using FacultyManager.Model.Operations.ClientOperations;

namespace FacultyManager.Model
{
    public class FacultyController
    {
        private ConnectionHandler connectionHandler;
        private EncoderDecoder encoderDecoder;
        private bool running = true;
        public FacultyController()
        {
            String serverAddress = "";
            Int32 serverPort = 0;
            connectionHandler = new ConnectionHandler(serverAddress, serverPort);
            encoderDecoder = new EncoderDecoder();
            Thread thr = new Thread(ServerListener);
            thr.Start();
        }
        private void ServerListener()
        {
            /*while (running)
            {
                byte[] message = connectionHandler.Receive();
                ServerOperation ServerResponse = null;
                int offset = 0;
                while (ServerResponse == null)
                    ServerResponse = encoderDecoder.decodeNextByte(message[offset++]);
                // ServerResponse.execute - 
            }*/
        }
        internal IEnumerable<CourseModel> GetAllCourses(UserModel user)
        {
            throw new NotImplementedException();
        }

        public void Login(string email, string password)
        {
            connectionHandler.Send(encoderDecoder.encode(new LoginOperation(2, email, password)));
        }
    }
}
