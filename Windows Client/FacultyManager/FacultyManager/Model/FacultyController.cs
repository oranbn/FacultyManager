using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

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

            byte[] message = connectionHandler.Receive();
            NotifiableModelObject ServerResponse = null;
            int offset = 0;
            while (ServerResponse == null)
                ServerResponse = encoderDecoder.decodeNextByte(message[offset++]);
            // ServerResponse.execute - 
        }
        internal IEnumerable<CourseModel> GetAllCourses(UserModel user)
        {
            throw new NotImplementedException();
        }

    }
}
