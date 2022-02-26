using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class LogoutOperation : ClientOperation
    {
        public LogoutOperation(short opCode) : base(opCode) { }
        
        public override byte[] encode() {
            byte[] bytes = new byte[2];
            bytes[0] = (byte) ((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte) (getOpCode() & 0xFF);
            return bytes;
        }
    }
}