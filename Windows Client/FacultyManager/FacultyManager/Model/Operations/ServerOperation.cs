using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace FacultyManager.Model.Operations
{
    public abstract class ServerOperation : Operation
    {
        private readonly short opCode;
        public int Length;
        byte[] bArr;
        private readonly int size=12;

        public ServerOperation(short opCode) {
            this.opCode = opCode;
            bArr = new byte[size];
            Length = 0;
        }
        public short getOpCode() {
            return opCode;
        }
        public abstract bool pushByte(byte nextByte);
        public void pushNextByte(byte nextByte) {
            if (Length >= bArr.Length) {
                Array.Resize(ref bArr, Length * 2);
            }
            bArr[Length++] = nextByte;
        }

        public short bytesToShort()
        {
            short result = (short)((bArr[0] & 0xff) << 8);
            result += (short)(bArr[1] & 0xff);
            bArr = new byte[size];
            Length = 0;
            return result;
        }
        public String bytesToString(){
            String output = System.Text.Encoding.UTF8.GetString(bArr);
            bArr = new byte[size];
            Length = 0;
            return output;
        }
        /*public int bytesToInt()
        {
            byte[] bytearray = {bArr[0],bArr[1],bArr[2],bArr[3]};
            int output = BitConverter.ToInt32(bytearray, 0);
            bArr = new byte[size];
            Length = 0;
            return output;
        }
        public double bytesToDouble()
        {
            double output = BitConverter.ToDouble(bArr, 0);
            bArr = new byte[size];
            Length = 0;
            return output;
        }*/
        public bool bytesToBoolean(){
            int temp = Length;
            Length = 0;
            if(temp > 1 || bArr[0] == (byte)1) {
                bArr = new byte[size];
                return false;
            }
            bArr = new byte[size];
            return true;
        }

    }
}
