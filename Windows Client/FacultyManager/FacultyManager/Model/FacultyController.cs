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
        public ServerOperation SendMessage(byte[] message)
        {
            connectionHandler.Send(message);
            lock(_lock)
                while (_serverOperation == null)
                    Monitor.Wait(_lock);
            ServerOperation serverOperation = _serverOperation;
            _serverOperation = null;
            return serverOperation;
        }

        public ServerOperation AcceptFriendRequest(string email) {
            return SendMessage(encoderDecoder.encode(new AcceptFriendRequestOperation(12, email)));

        }

        public ServerOperation AddAnswer(int questionId, int examId, int courseId, string content, bool correct)
        {
            return SendMessage(encoderDecoder.encode(new AddAnswerOperation(13, questionId, examId, courseId, content, correct)));

        }

        public ServerOperation AddChat(int courseId, string chatName)
        {
            return SendMessage(encoderDecoder.encode(new AddChatOperation(14, courseId, chatName)));

        }

        public ServerOperation AddForbiddenWord(string forbiddenWord){    
            return SendMessage(encoderDecoder.encode(new AddForbiddenWordOperation(15, forbiddenWord)));
        }
        public ServerOperation CancelFriendShip(string email){
            return SendMessage(encoderDecoder.encode(new CancelFriendshipOperation(16, email)));
        }
        public ServerOperation ChangeAnswerContent(int answerId, int questionId, int examId, int courseId, string content){        
            return SendMessage(encoderDecoder.encode(new ChangeAnswerContentOperation(17, answerId, questionId, examId, courseId, content)));
        }

        public ServerOperation ChangeAnswerCorrect(int answerId, int questionId, int examId, int courseId, bool correct) {
            return SendMessage(encoderDecoder.encode(new ChangeAnswerCorrectOperation(18, answerId, questionId, examId, courseId, correct)));

        }

        public ServerOperation ChangeChatMessageContent(int messageId, int chatId, int courseId, string content) {
            return SendMessage(encoderDecoder.encode(new ChangeChatMessageContentOperation(19, messageId, chatId, courseId, content)));

        }

        public ServerOperation ChangeCourseName() {
            //return SendMessage(encoderDecoder.encode(new ChangeCourseNameOperation(20)));
            return null;
        }

        public ServerOperation ChangePassword(string password) {
            return SendMessage(encoderDecoder.encode(new ChangePasswordOperation(21, password)));

        }

        public ServerOperation ChangeQuestionTitle() {
            //return SendMessage(encoderDecoder.encode(new ChangeQuestionTitleOperation(22, email)));
            return null;
        }

        public ServerOperation ChangeUserPermission(string email, int permission) {
            return SendMessage(encoderDecoder.encode(new ChangeUserPermissionOperation(23, email, permission)));

        }

        public ServerOperation ChatMessage(int courseId, int chatId, string time, string content) {
            return SendMessage(encoderDecoder.encode(new ChatMessageOperation(24, courseId, chatId, time, content)));

        }
        public ServerOperation CreateCourse(string name, string generalInfo){   
            return SendMessage(encoderDecoder.encode(new CreateCourseOperation(25, name, generalInfo)));
        }
        public ServerOperation CreateExam(int courseId, int duration, string examDate){   
            return SendMessage(encoderDecoder.encode(new CreateExamOperation(26, courseId, duration, examDate)));
        }
        public ServerOperation CreateNewChat(int courseId, string chatName){   
            return SendMessage(encoderDecoder.encode(new CreateNewChatOperation(27, courseId, chatName)));
        }
        public ServerOperation GetAllChatMessages(int courseId, int chatId){   
            return SendMessage(encoderDecoder.encode(new GetAllChatMessagesOperation(28, courseId, chatId)));
        }
        internal IEnumerable<CourseModel> GetAllCourses(UserModel user)
        {
            throw new NotImplementedException();
        }
        public ServerOperation Login(string email, string password)
        {
            return SendMessage(encoderDecoder.encode(new LoginOperation(2, email, password)));
        }

        public ServerOperation Logout()
        {
            return SendMessage(encoderDecoder.encode(new LogoutOperation(3)));
        }

        public ServerOperation MarkMessage(int courseId, int chatId, int messageId) {
            return SendMessage(encoderDecoder.encode(new MarkMessageOperation(4,courseId, chatId, messageId)));
        }

        public ServerOperation PrivateMessage(string userName, string content, string dateAndTime) {
            return SendMessage(encoderDecoder.encode(new PrivateMessageOperation(5,userName, content, dateAndTime)));
        }
        public ServerOperation Register(string email, string password, string firstName, string lastName, string idNumber,
            string phoneNumber, string birthday) {
            return SendMessage(encoderDecoder.encode(new RegisterOperation(1, email, password, firstName, lastName, idNumber, phoneNumber, "22-03-1998")));
        }

        public ServerOperation RemoveAnswer(int questionId, int answerId, int examId, int courseId)
        {
            return SendMessage(encoderDecoder.encode(new RemoveAnswerOperation(6, questionId, answerId, examId, courseId)));
        }

        public ServerOperation RemoveChatMessage(int courseId, int chatId, int messageId) {
            return SendMessage(encoderDecoder.encode(new RemoveChatMessageOperation(7, courseId, chatId, messageId)));
        }

        public ServerOperation RemoveChat(int courseId, int chatId) {
            return SendMessage(encoderDecoder.encode(new RemoveChatOperation(8, courseId, chatId)));
        }

        public ServerOperation RemoveForbiddenWord(string forbiddenWord) {
            return SendMessage(encoderDecoder.encode(new RemoveForbiddenWordOperation(9, forbiddenWord)));
        }
        public ServerOperation SendFriendRequest(string email) {
            return SendMessage(encoderDecoder.encode(new SendFriendRequestOperation(10, email)));
        }

        public ServerOperation UnMarkMessage(int courseId, int chatId, int messageId)
        {
            return SendMessage(encoderDecoder.encode(new UnMarkMessageOperation(11, courseId, chatId, messageId)));
        }

        public ServerOperation ActivateAccount(string activationCode)
        {
            return SendMessage(encoderDecoder.encode(new ActivateAccountOperation(29, activationCode)));
        }

        public ServerOperation ResetPassword(string newPassword, string email)
        {
            return SendMessage(encoderDecoder.encode(new ResetPasswordOperation(31, newPassword, email)));
        }

        public ServerOperation ForgotPassword(string email)
        {
            return SendMessage(encoderDecoder.encode(new ForgotPasswordOperation(30, email)));
        }

        public ServerOperation ForgotPasswordCode(string forgotPasswordCode, string email)
        {
            return SendMessage(encoderDecoder.encode(new ForgotPasswordCodeOperation(32, forgotPasswordCode, email)));
        }
    }
}