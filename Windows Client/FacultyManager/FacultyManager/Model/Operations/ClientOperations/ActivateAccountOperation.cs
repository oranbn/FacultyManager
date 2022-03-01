using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ActivateAccountOperation: ClientOperation
    {
        private readonly string _activationCode;

        public ActivateAccountOperation(short opCode, string activationCode) : base(opCode) {
            this._activationCode = activationCode;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_activationCode.Length + 4];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_activationCode, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}