using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class AcceptFriendRequestOperation : ClientOperation
    {
        private readonly string _email;

        public AcceptFriendRequestOperation(short opCode, string email) : base(opCode)
        {
            this._email = email;
        }

        public override byte[] encode()
        {
            byte[] bytes = new byte[_email.Length + 4];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_email, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
