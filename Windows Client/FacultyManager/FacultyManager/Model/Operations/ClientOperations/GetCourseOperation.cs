using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class GetCourseOperation : ClientOperation
    {
        private readonly int _courseId;

        public GetCourseOperation(short opCode, int courseId) : base(opCode) {
            _courseId = courseId;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[8];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
