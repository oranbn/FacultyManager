using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ChangeChatMessageContentOperation : ClientOperation
    {
        private readonly int _messageId;
        private readonly int _chatId;
        private readonly int _courseId;
        private readonly string _content;

        public ChangeChatMessageContentOperation(short opCode, int messageId, int chatId, int courseId, string content) : base(opCode) {
            _messageId = messageId;
            _chatId = chatId;
            _courseId = courseId;
            _content = content;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_content.Length + 19];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_messageId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_chatId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_content, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
