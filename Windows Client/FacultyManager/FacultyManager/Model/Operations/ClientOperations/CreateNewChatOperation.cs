using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class CreateNewChatOperation : ClientOperation
    {
        private readonly int _courseId;
        private readonly string _chatName;

        public CreateNewChatOperation(short opCode, int courseId, string chatName) : base(opCode) {
            _courseId = courseId;
            _chatName = chatName;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_chatName.Length + 9];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_chatName, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
