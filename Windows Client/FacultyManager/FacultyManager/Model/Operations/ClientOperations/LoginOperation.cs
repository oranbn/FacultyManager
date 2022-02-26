using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class LoginOperation : ClientOperation
    {
        private readonly string _email;
        private readonly string _password;

        public LoginOperation(short opCode, string email, string password) : base(opCode)
        {
            _email = email;
            _password = password;
        }

        public override byte[] encode()
        {
            byte[] bytes = new byte[_email.Length + _password.Length + 4];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_email, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_password, bytes, ref index);
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
