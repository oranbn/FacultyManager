using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ChangeAnswerCorrectOperation : ClientOperation
    {
        private readonly int _answerId;
        private readonly int _questionId;
        private readonly int _examId;
        private readonly int _courseId;
        private readonly bool _correct;

        public ChangeAnswerCorrectOperation(short opCode, int answerId, int questionId, int examId, int courseId, bool correct) : base(opCode) {
            this._answerId = answerId;
            this._questionId = questionId;
            this._examId = examId;
            this._courseId = courseId;
            this._correct = correct;
        }
        public override byte[] encode()
        {
            byte[] bytes = new byte[25];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_answerId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_questionId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_examId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            AddBooleanToByteArray(_correct, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
