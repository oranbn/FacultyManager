using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class PrivateMessageOperation : ClientOperation
    {
        private readonly string _userName;
        private readonly string _content;
        private readonly string _dateAndTime;

        public PrivateMessageOperation(short opCode, string userName, string content, string dateAndTime) : base(opCode) {
            _userName = userName;
            _content = content;
            _dateAndTime = dateAndTime;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_userName.Length + _content.Length + _dateAndTime.Length + 6];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_userName, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_content, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_dateAndTime, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
