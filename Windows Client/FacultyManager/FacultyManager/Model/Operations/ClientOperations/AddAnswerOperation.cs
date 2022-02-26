using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class AddAnswerOperation : ClientOperation
    {
        private readonly int _questionId;
        private readonly int _examId;
        private readonly int _courseId;
        private readonly string _content;
        private readonly bool _correct;

        public AddAnswerOperation(short opCode, int questionId, int examId, int courseId, string content, bool correct) : base(opCode)
        {
            this._questionId = questionId;
            this._examId = examId;
            this._courseId = courseId;
            this._content = content;
            this._correct = correct;
        }
        public override byte[] encode()
        {
            byte[] bytes = new byte[_content.Length + 21];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddIntToByteArray(_questionId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_examId, bytes, ref index);
            bytes[index++] = 0;
            AddIntToByteArray(_courseId, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_content, bytes, ref index);
            bytes[index++] = 0;
            AddBooleanToByteArray(_correct, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
