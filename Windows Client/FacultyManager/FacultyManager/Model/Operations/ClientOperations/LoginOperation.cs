using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    class LoginOperation : ClientOperation
    {
        private string email;
        private string password;

        public LoginOperation(short opCode, string email, string password) : base(opCode)
        {
            this.email = email;
            this.password = password;
        }

        public override byte[] encode()
        {
            byte[] bytes = new byte[email.Length + password.Length + 4];
            bytes[0] = (byte)((base.getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(base.getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(email, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(password, bytes, ref index);
            bytes[index++] = (byte)';';
            return bytes;
        }
    }
}
