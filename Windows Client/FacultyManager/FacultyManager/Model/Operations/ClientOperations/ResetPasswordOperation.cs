using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ResetPasswordOperation : ClientOperation
    {
        private readonly string _password;
        private readonly string _email;

        public ResetPasswordOperation(short opCode, string password, string email) : base(opCode) {
            _password = password;
            _email = email;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_password.Length + _email.Length + 5];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_password, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_email, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}