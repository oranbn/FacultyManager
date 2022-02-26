using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ChangeUserPermissionOperation : ClientOperation
    {
        private readonly string _email;
        private readonly int _permission;

        public ChangeUserPermissionOperation(short opCode, string email, int permission) : base(opCode)
        {
            _email = email;
            _permission = permission;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_email.Length + 9];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_email, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_permission, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
