using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class CreateExamOperation : ClientOperation
    {
        private readonly int _courseId;
        private readonly int _duration;
        private readonly string _examDate;

        public CreateExamOperation(short opCode, int courseId, int duration, string examDate) : base(opCode) {
            _courseId = courseId;
            _duration = duration;
            _examDate = examDate;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_examDate.Length + 14];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_duration, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_examDate, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
