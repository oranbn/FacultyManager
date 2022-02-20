using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations
{
    abstract public class ClientOperation : Operation
    {
        private readonly short opCode;
        private int length;
        byte[] bArr;
        private readonly int size = 12;

        public ClientOperation(short opCode)
        {
            this.opCode = opCode;
            bArr = new byte[size];
            this.length = 0;
        }
        public short getOpCode()
        {
            return opCode;
        }
        public abstract byte[] encode();
        public void AddStringToByteArray(string str, byte[] bytes, ref int index)
        {
            for (int i = 0; i < str.Length; i++, index++)
                bytes[i + index] = (byte)str[i];
        }
    }
}
