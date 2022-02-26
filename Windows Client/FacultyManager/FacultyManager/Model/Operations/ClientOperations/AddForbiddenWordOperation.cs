using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class AddForbiddenWordOperation: ClientOperation
    {
        private readonly string _forbiddenWord;

        public AddForbiddenWordOperation(short opCode, string forbiddenWord) : base(opCode) {
            this._forbiddenWord = forbiddenWord;
        }
        public override byte[] encode() {
            byte[] bytes = new byte[_forbiddenWord.Length + 4];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_forbiddenWord, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}
