using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations
{
    abstract public class ClientOperation : Operation {
        private readonly short opCode;
        private int length;
        byte[] bArr;
        private readonly int size = 12;

        public ClientOperation(short opCode) {
            this.opCode = opCode;
            bArr = new byte[size];
            this.length = 0;
        }
        public short getOpCode() {
            return opCode;
        }
        public abstract byte[] encode();
        public void AddStringToByteArray(string str, byte[] bytes, ref int index) {
            for (int i = 0; i < str.Length; i++)
                bytes[index++] = (byte)str[i];
        }
        public void AddIntToByteArray(int value, byte[] bytes, ref int index) {
            bytes[index++] = BitConverter.GetBytes(value)[0];
            bytes[index++] = BitConverter.GetBytes(value)[1];
            bytes[index++] = BitConverter.GetBytes(value)[2];
            bytes[index++] = BitConverter.GetBytes(value)[3];
        }

        public void AddBooleanToByteArray(bool value, byte[] bytes, ref int index)
        {
            if (value)
                bytes[index++] = 2;
            else
                bytes[index++] = 1;

        }

    }
}
