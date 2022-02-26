using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class GetAllChatMessagesOperation : ClientOperation
    {
        private readonly int _courseId;
        private readonly int _chatId;

        public GetAllChatMessagesOperation(short opCode, int courseId, int chatId) : base(opCode) {
            _courseId = courseId;
            _chatId = chatId;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[13];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_chatId, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
