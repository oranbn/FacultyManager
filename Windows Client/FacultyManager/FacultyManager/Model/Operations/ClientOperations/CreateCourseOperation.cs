using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class CreateCourseOperation : ClientOperation
    {    
        private readonly string _name;
        private readonly string _generalInfo;

        public CreateCourseOperation(short opCode, string name, string generalInfo) : base(opCode) {
            _name = name;
            _generalInfo = generalInfo;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_name.Length + _generalInfo.Length + 5];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_name, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_generalInfo, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
