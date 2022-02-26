using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations
{
    public abstract class ServerOperation : Operation
    {
        private readonly short opCode;
        private int length;
        byte[] bArr;
        private readonly int size=12;

        public ServerOperation(short opCode) {
            this.opCode = opCode;

        }
        public short getOpCode() {
            return opCode;
        }
        public abstract bool pushByte(byte nextByte);
        public void pushNextByte(byte nextByte) {
            if (length >= bArr.Length) {
                Array.Resize(ref bArr, length * 2);
            }
            bArr[length++] = nextByte;
        }

        public short bytesToShort()
        {
            short output = BitConverter.ToInt16(bArr, 0);
            bArr = new byte[size];
            length = 0;
            return output;
        }
        public String bytesToString(){
            String output = System.Text.Encoding.UTF8.GetString(bArr);
            bArr = new byte[size];
            length = 0;
            return output;
        }
        public int bytesToInt(){
            int output = BitConverter.ToInt32(bArr, 0);
            bArr = new byte[size];
            length = 0;
            return output;
        }
        public double bytesToDouble()
        {
            double output = BitConverter.ToDouble(bArr, 0);
            bArr = new byte[size];
            length = 0;
            return output;
        }
        public bool bytesToBoolean(){
            bArr = new byte[size];
            int temp = length;
            length = 0;
            if(temp > 1 || bArr[0] == 1) {
                return false;
            }
            return true;
        }

    }
}
