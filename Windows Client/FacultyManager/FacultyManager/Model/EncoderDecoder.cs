using FacultyManager.Model.Operations;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model
{
    public class EncoderDecoder
    {
        int length = 0;
        private byte[] bytes = new byte[1 << 10];
        short opCode = -1;
        Operation operation = null;
        public EncoderDecoder()
        {

        }
        public Operation decodeNextByte(byte nextByte)
        {
            return null;
        }
        public byte[] encode(Operation message)
        {
            return null;
        }
}