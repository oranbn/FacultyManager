using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class RemoveAnswerOperation : ClientOperation
    {
        private readonly int _questionId;
        private readonly int _answerId;
        private readonly int _examId;
        private readonly int _courseId;

        public RemoveAnswerOperation(short opCode, int questionId, int answerId, int examId, int courseId) : base(opCode) {
            _questionId = questionId;
            _answerId = answerId;
            _examId = examId;
            _courseId = courseId;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[23];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_questionId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_answerId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_examId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
