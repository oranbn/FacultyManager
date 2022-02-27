using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Threading;
using System.Windows;
using FacultyManager.Model.Operations;
using FacultyManager.Model.Operations.ClientOperations;
using FacultyManager.Stores;

namespace FacultyManager.Model
{
    public class FacultyController
    {
        private ConnectionHandler connectionHandler;
        private EncoderDecoder encoderDecoder;
        private AccountStore _accountStore;
        private bool running = true;
        private object _lock = new object();
        private ServerOperation _serverOperation;
        public FacultyController(AccountStore accountStore)
        {
            _accountStore = accountStore;
            String serverAddress = "127.0.0.1";
            Int32 serverPort = 8888;
            connectionHandler = new ConnectionHandler(serverAddress, serverPort);
            encoderDecoder = new EncoderDecoder();
            Thread thr = new Thread(ServerListener);
            thr.Start();
        }
        private void ServerListener()
        {
            while (running)
            {
                byte[] message = connectionHandler.Receive();
                ServerOperation ServerResponse = null;
                int offset = 0;
                while (ServerResponse == null)
                    ServerResponse = encoderDecoder.decodeNextByte(message[offset++]);
                _serverOperation = ServerResponse;
                lock (_lock)
                    Monitor.PulseAll(_lock);
            }
        }
        public void AcceptFriendRequest(string email){}
        public void AddAnswer(int questionId, int examId, int courseId, string content, bool correct){}
        public void AddChat(int courseId, string chatName) { }

        public void AddForbiddenWord(string forbiddenWord){ }
        public void CancelFriendShip(string email){}
        public void ChangeAnswerContent(int answerId, int questionId, int examId, int courseId, string content){}
        public void ChangeAnswerCorrect(int answerId, int questionId, int examId, int courseId, bool correct) {}
        public void ChangeChatMessageContent(int messageId, int chatId, int courseId, string content) {}
        public void ChangeCourseName(){}
        public void ChangePassword(string password){}
        public void ChangeQuestionTitle(){}
        public void ChangeUserPermission(string email, int permission){}
        public void ChatMessage(int courseId, int chatId, string time, string content){}
        public void CreateCourse(string name, string generalInfo){}
        public void CreateExam(int courseId, int duration, string examDate){}
        public void CreateNewChat(int courseId, string chatName){}
        public void GetAllChatMessages(int courseId, int chatId){}
        internal IEnumerable<CourseModel> GetAllCourses(UserModel user)
        {
            throw new NotImplementedException();
        }
        public ServerOperation Login(string email, string password)
        {
            connectionHandler.Send(encoderDecoder.encode(new LoginOperation(2, email, password)));
            lock(_lock)
                while (_serverOperation == null)
                    Monitor.Wait(_lock);
            ServerOperation serverOperation = _serverOperation;
            _serverOperation = null;
            return serverOperation;
        }

        public ServerOperation Logout()
        {
            connectionHandler.Send(encoderDecoder.encode(new LogoutOperation(3)));
            lock(_lock)
                while (_serverOperation == null)
                    Monitor.Wait(_lock);
            ServerOperation serverOperation = _serverOperation;
            _serverOperation = null;
            return serverOperation;
        }

        public ServerOperation MarkMessage(int courseId, int chatId, int messageId) {
            connectionHandler.Send(encoderDecoder.encode(new MarkMessageOperation(4,courseId, chatId, messageId)));
            lock(_lock)
                while (_serverOperation == null)
                    Monitor.Wait(_lock);
            ServerOperation serverOperation = _serverOperation;
            _serverOperation = null;
            return serverOperation;
        }

        public ServerOperation PrivateMessage(string userName, string content, string dateAndTime) {
            connectionHandler.Send(encoderDecoder.encode(new PrivateMessageOperation(5,userName, content, dateAndTime)));
            lock(_lock)
                while (_serverOperation == null)
                    Monitor.Wait(_lock);
            ServerOperation serverOperation = _serverOperation;
            _serverOperation = null;
            return serverOperation;
        }
        public ServerOperation Register(string email, string password, string firstName, string lastName, string idNumber,
            string phoneNumber, string birthday) {
            connectionHandler.Send(encoderDecoder.encode(new RegisterOperation(1, email, password, firstName, lastName, idNumber, phoneNumber, "22-03-1998")));
            lock(_lock)
                while (_serverOperation == null)
                    Monitor.Wait(_lock);
            ServerOperation serverOperation = _serverOperation;
            _serverOperation = null;
            return serverOperation;
        }

        public ServerOperation RemoveAnswer(int questionId, int answerId, int examId, int courseId)
        {
            connectionHandler.Send(encoderDecoder.encode(new RemoveAnswerOperation(6, questionId, answerId, examId, courseId)));
            lock(_lock)
                while (_serverOperation == null)
                    Monitor.Wait(_lock);
            ServerOperation serverOperation = _serverOperation;
            _serverOperation = null;
            return serverOperation;
        }
        public void RemoveChatMessage(int courseId, int chatId, int messageId){}
        public void RemoveChat(int courseId, int chatId){}
        public void RemoveForbiddenWord(string forbiddenWord){}
        public void SendFriendRequest(string email){}
        public void UnMarkMessage(int courseId, int chatId, int messageId){}
    }
}