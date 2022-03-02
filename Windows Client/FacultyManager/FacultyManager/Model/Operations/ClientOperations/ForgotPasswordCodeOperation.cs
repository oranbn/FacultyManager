using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ForgotPasswordCodeOperation : ClientOperation
    {
        private readonly string _forgotPasswordCode;
        private readonly string _email;

        public ForgotPasswordCodeOperation(short opCode, string forgotPasswordCode, string email) : base(opCode) {
            _forgotPasswordCode = forgotPasswordCode;
            _email = email;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_forgotPasswordCode.Length + _email.Length + 5];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_forgotPasswordCode, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_email, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}