using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ChatMessageOperation : ClientOperation
    {
        private readonly int _courseId;
        private readonly int _chatId;
        private readonly string _time;
        private readonly string _content;

        public ChatMessageOperation(short opCode, int courseId, int chatId, string time, string content) : base(opCode) {
            _courseId = courseId;
            _chatId = chatId;
            _time = time;
            _content = content;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_time.Length + _content.Length + 15];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_chatId, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_time, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_content, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
