using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class ChangeAnswerContentOperation : ClientOperation
    {
        private readonly int _answerId;
        private readonly int _questionId;
        private readonly int _examId;
        private readonly int _courseId;
        private readonly string _content;

        public ChangeAnswerContentOperation(short opCode, int answerId, int questionId, int examId, int courseId, string content) : base(opCode) {
            this._answerId = answerId;
            this._questionId = questionId;
            this._examId = examId;
            this._courseId = courseId;
            this._content = content;
        }
        public override byte[] encode()
        {
            byte[] bytes = new byte[_content.Length + 24];
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
            AddStringToByteArray(_content, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
